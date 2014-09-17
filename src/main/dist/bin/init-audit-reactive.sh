#!/bin/bash
# init-audit-reactive script
#
# Environment:
# JAVA_HOME - location of a JDK home dir (optional if java on path)
# AUDIT_REACTIVE_HOME - location of a Audit Reactive home dir (optional)
# FRAMEWORKS_HOME - The <home>/etc/ directory with the default properties for frameworks (optional)
#
# Parameters:
# -help       : Print help (optional)
# -s          : Silent mode (optional)
# <framework> : Framework name. Must be "jetty", "catalina" or "play". (optional)
#
# Set:
# AUDIT_OPTS  - JVM options for audit
# *_OPTS      - If <framework> would like.
#
set -e

usage() {
    cat <<_EOF
    Usage init-audit-reactive [options] [framework]
    Framework:
    <nothing>  Set AUDIT_OPTS
    jetty      Set AUDIT_OPTS to start jetty
    catalina   Set CATALINA_OPTS
    play       Set SBT_OPTS

    ant        Set ANT_OPTS
    gradle     Set GRADLE_OPTS
    maven      Set MAVEN_OPTS
    sbt        Set SBT_OPTS

    help       Print this message

    Options:
    -s Silent mode
    -d Debug mode. Print values

    Environment variables ^(read from context^):
    AUDIT_REACTIVE_HOME  Env. variable, if unset uses the home directory of this batch.
    FRAMEWORKS_HOME      Env. variable, if unset uses AUDIT_REACTIVE_HOME/etc

    JRE_HOME             Env. variable, if unset detect the value with PATH
    JAVA_HOME            Env. variable, if unset detect the value with PATH
    JAVACMD              Env. variable, if unset use "java"
_EOF
}

echoerr () {
  echo 1>&2 "$@"
}
vlog () {
  [[ $verbose || $debug ]] && echoerr "$@"
}
dlog () {
  [[ $debug ]] && echoerr "$@"
}


realpath () {
(
  TARGET_FILE="$1"

  cd $(dirname "$TARGET_FILE")
  TARGET_FILE=$(basename "$TARGET_FILE")

  COUNT=0
  while [ -L "$TARGET_FILE" -a $COUNT -lt 100 ]
  do
      TARGET_FILE=$(readlink "$TARGET_FILE")
      cd $(dirname "$TARGET_FILE")
      TARGET_FILE=$(basename "$TARGET_FILE")
      COUNT=$(($COUNT + 1))
  done

  if [ "$TARGET_FILE" == "." -o "$TARGET_FILE" == ".." ]; then
    cd "$TARGET_FILE"
    TARGET_FILEPATH=
  else
    TARGET_FILEPATH=/$TARGET_FILE
  fi

  # make sure we grab the actual windows path, instead of cygwin's path.
  if ! is_cygwin; then
    echo "$(pwd -P)/$TARGET_FILE"
  else
    echo $(cygwinpath "$(pwd -P)/$TARGET_FILE")
  fi
)
}

# Uses uname to detect if we're in the odd cygwin environment.
is_cygwin() {
  local os=$(uname -s)
  case "$os" in
    CYGWIN*) return 0 ;;
    *)  return 1 ;;
  esac
}

# This can fix cygwin style /cygdrive paths so we get the
# windows style paths.
cygwinpath() {
  local file="$1"
  if is_cygwin; then
    echo $(cygpath -w -s $file)
  else
    echo $file
  fi
}

# Generates Paths format
cygwinpaths() {
  shift
  if is_cygwin; then
      local files=$(cygwinpath $1)
      for file in $*
      do
        files="$files;$(cygwinpath $file)"
      done
  else
      local files=$(cygunixpath $1)
      for file in $*
      do
        files="$files:$(cygunixpath $file)"
      done
  fi
  echo $files
}

cygunixpath() {
  local file="$1"
  if is_cygwin; then
    echo $(cygpath -u $file)
  else
    echo $file
  fi
}

# Detect if we should use JAVA_HOME or just try PATH.
get_jre_home() {
dlog "JRE_HOME=$JRE_HOME"
dlog "JAVA_HOME=$JAVA_HOME"

  if [[ -n "$JRE_HOME" ]] ; then
    echo "$(cygunixpath "$JRE_HOME")"
  else
    if [[ -n "$JAVA_HOME" ]] ; then
        echo "$(cygunixpath "$JAVA_HOME")"
    else
        echoerr "A Java JRE is not installed or can't be found."
        return 1
    fi
  fi
}

get_java_cmd() {
  if [[ -n "$jre_home" ]] && [[ -x "$jre_home/bin/java" ]];  then
    echo "$jre_home/bin/java"
  else
    echo "$(which java)"
  fi
}

# Processes incoming arguments and places them in appropriate global variables.  called by the run method.
process_args () {
  while [[ $# -gt 0 ]]; do
    case "$1" in
       -h|-help) usage; return 1 ;;
             -s) silent=1 && shift ;;
             -d) debug=1 && shift ;;
        -sd|-ds) silent=1 && debug=1 && shift ;;
              *) framework=$1 && conf=-DauditReactive="$(cygwinpath "${FRAMEWORKS_HOME}/$1.properties")" &&  shift ;;
    esac
  done
}


###  ------------------------------- ###
###  Main script                     ###
###  ------------------------------- ###

if [[ -z "${AUDIT_REACTIVE_HOME}" ]]
then
  declare AUDIT_REACTIVE_HOME="$(dirname $(realpath "../$0"))"
else
  declare AUDIT_REACTIVE_HOME="$(realpath "${AUDIT_REACTIVE_HOME}")"
fi

if [[ -z "${FRAMEWORKS_HOME}" ]]
then
  FRAMEWORKS_HOME="$(cygwinpath ${AUDIT_REACTIVE_HOME}/etc)"
else
  FRAMEWORKS_HOME="$(realpath "${FRAMEWORKS_HOME}")"
fi

jre_home=$(get_jre_home)
java_cmd=$(get_java_cmd)
export AUDIT_REACTIVE_HOME
#export FRAMEWORKS_HOME
export AUDIT_OPTS

#declare -i debug
process_args $*

# Now check to see if it's a good enough version
java_version=$("$java_cmd" -version 2>&1 | awk -F '"' '/version/ {print $2}')
if [[ "$java_version" == "" ]]; then
  echo
  echo Please go to
  echo   http://www.oracle.com/technetwork/java/javase/downloads/index.html
  echo and download a valid Java JDK and install before running audit-reactive.
  echo
  echo If you think this message is in error, please check
  echo your environment variables to see if "java" is
  echo available via JAVA_HOME, JRE_HOME or PATH.
  echo
elif [[ ! "$java_version" > "1.7" ]]; then
  echo
  echo The java installation you have is not up to date
  echo audit-reactive requires at least version 1.7+, you have
  echo version $java_version
  echo
  echo Please go to http://www.java.com/getjava/ and download
  echo a valid Java Runtime and install before running Activator.
  echo
else

weaver=-javaagent:$(cygwinpath "$AUDIT_REACTIVE_HOME/lib/aspectjweaver.jar")
# Add audit agent with bootclasspath
# strategy with Xbootclasspath
# xboot=-Xbootclasspath/p:${AUDIT_REACTIVE_HOME}/lib/aspectjweaver.jar:${AUDIT_REACTIVE_HOME}/lib/audit-reactive.jar:${AUDIT_REACTIVE_HOME}/lib/audit-reactive-lib.jar
# AUDIT_OPTS=${conf} ${weaver} ${xboot}

# Add audit agent with java.ext.dirs
if [[ -e "${jre_home}/jre/lib/ext" ]]; then
  ext="${jre_home}/jre/lib/ext"
elif [[ -e "${jre_home}/lib/ext" ]]; then
  ext="${jre_home}/lib/ext"
else
  echoerr "Not found JAVA_HOME/lib/ext"
  return 1
fi
extdir="-Djava.ext.dirs=$(cygwinpaths "$ext" "${AUDIT_REACTIVE_HOME}/lib")"
AUDIT_OPTS="${conf} ${weaver} ${extdir}"

if [[ -n "$debug" ]]; then
dlog "AUDIT_REACTIVE_HOME = $AUDIT_REACTIVE_HOME"
dlog "FRAMEWORKS_HOME     = $FRAMEWORKS_HOME"
dlog "JRE_HOME            = $jre_home"
dlog "EXT                 = $ext"
dlog "JAVACMD             = $java_cmd"
dlog ""
dlog "AUDIT_OPTS=$AUDIT_OPTS"
dlog ""
fi

if [[ -z "$framework" ]]; then
    if [[ -z "$silent" ]]; then
        echo 'Now you can use the environment variable AUDIT_OPTS for launch Java program.'
        echo ' set JAVA_OPTS=$AUDIT_OPTS'
        echo 'Add -DauditReactive=<yourfile>.properties to select a specific configuration.'
    fi

elif [[ "$framework" == "jboss" ]] ; then
    echo Not yet implemented

elif [[ "$framework" == "play" ]]; then
    export SBT_OPTS=$AUDIT_OPTS
    if [[ -z "$silent" ]]; then
        echo "SBT_OPTS was set. You can use TypeSafe 'activator run'."
    fi
elif [[ "$framework" == "ant" ]]; then
    export ANT_OPTS=$AUDIT_OPTS
    if [[ -z "$silent" ]]; then
        echo "ANT_OPTS was set. You can use 'ant'."
    fi
elif [[ "$framework" == "sbt" ]]; then
    export SBT_OPTS=$AUDIT_OPTS
    if [[ -z "$silent" ]]; then
        echo "SBT_OPTS was set. You can use TypeSafe 'activator run'."
    fi
elif [[ "$framework" == "maven" ]]; then
    export MAVEN_OPTS=$AUDIT_OPTS
    if [[ -z "$silent" ]]; then
        echo "MAVEN_OPTS was set. You can use 'mvn'."
    fi
elif [[ "$framework" == "gradle" ]]; then
    export GRADLE_OPTS=$AUDIT_OPTS
    if [[ -z "$silent" ]]; then
        echo "GRADLE_OPTS was set. You can use 'gradle'."
    fi
elif [[ "$framework" == "jetty" ]]; then
    if [[ -z "$silent" ]]; then
        echo 'To start Jetty, use: java $AUDIT_OPTS -jar start.jar'
    fi
elif [[ "$framework" == "catalina" ]]; then
    export CATALINA_OPTS=$AUDIT_OPTS
    if [[ -z "$silent" ]]; then
        echo "CATALINA_OPTS was set. You can use 'catalina run'."
    fi
else
   echoerr "Framework $framework unknown !"
fi

fi  # java_version
