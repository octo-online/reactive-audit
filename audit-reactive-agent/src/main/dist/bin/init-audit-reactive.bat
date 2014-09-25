@REM init-audit-reactive script
@REM
@REM Environment:
@REM JAVA_HOME - location of a JDK home dir (optional if java on path)
@REM AUDIT_REACTIVE_HOME - location of a Audit Reactive home dir (optional)
@REM FRAMEWORKS_HOME - The <home>/etc/ directory with the default properties for frameworks (optional)
@REM
@REM Parameters:
@REM -help       : Print help (optional)
@REM -s          : Silent mode (optional)
@REM <framework> : Framework name. Must be "jetty", "catalina" or "play". (optional)
@REM
@REM Set:
@REM AUDIT_OPTS  - JVM options for audit
@REM *_OPTS      - If <framework> would like.
@REM
@REM same approach : http://manuals.zeroturnaround.com/jrebel/standalone/launch-from-command-line.html
@echo off
goto :ok
rem FIXME
VERIFY OTHER 2>nul
setlocal ENABLEEXTENSIONS
if ERRORLEVEL 0 goto ok
echo "Unable to enable extensions"
goto :end
:ok

REM ************************ Help
if "%1"=="help" (
  goto :help
)
if "%1"=="-help" (
  goto :help
)
if "%1"=="--help" (
  goto :help
)

REM ************************ set JAVA_HOME
set _JRE_HOME=%JRE_HOME%
set _JAVA_HOME=%JAVA_HOME%
set _AUDIT_REACTIVE_HOME=%AUDIT_REACTIVE_HOME%
set _FRAMEWORKS_HOME=%FRAMEWORKS_HOME%

rem Otherwise either JRE or JDK are fine
if not "%_JRE_HOME%" == "" goto gotJreHome
if not "%_JAVA_HOME%" == "" goto gotJavaHome
echo Neither the JAVA_HOME nor the JRE_HOME environment variable is defined
echo At least one of these environment variable is needed to run this program
goto end

rem Check if we have a usable JDK
if "%_JAVA_HOME%" == "" goto noJavaHome
if not exist "%_JAVA_HOME%\bin\java.exe" goto noJavaHome
set "_JRE_HOME=%JAVA_HOME%"
goto okJava

:noJavaHome
echo The JAVA_HOME environment variable is not defined correctly.
goto end

:gotJavaHome
rem No JRE given, use JAVA_HOME as JRE_HOME
set "_JRE_HOME=%_JAVA_HOME%"

:gotJreHome
rem Check if we have a usable JRE
if not exist "%_JRE_HOME%\bin\java.exe" goto noJreHome
goto okJava

:noJreHome
rem Needed at least a JRE
echo The JRE_HOME or JAVA_HOME environment variable is not defined correctly
echo This environment variable is needed to set parameters
goto end

:okJava

REM ************************ set JAVACMD

rem We use the value of the JAVACMD environment variable if defined
set _JAVACMD=%JAVACMD%

if "%_JAVACMD%"=="" (
  if not "%_JRE_HOME%"=="" (
    if exist "%_JRE_HOME%\bin\java.exe" set "_JAVACMD=%_JRE_HOME%\bin\java.exe"
  )
)

if "%_JAVACMD%"=="" set _JAVACMD=java

rem Detect if this java is ok to use.
for /F %%j in ('"%_JAVACMD%" -version  2^>^&1') do (
  if %%~j==Java set _JAVAINSTALLED=1
)

rem BAT has no logical or, so we do it OLD SCHOOL! Oppan Redmond Style
set _JAVAOK=true
if not defined _JAVAINSTALLED set _JAVAOK=false
set _JAVAINSTALLED=

if "%_JAVAOK%"=="false" (
  echo.
  echo A Java JRE is not installed or can't be found.
  if not "%_JAVA_HOME%"=="" (
    echo JAVA_HOME = "%JAVA_HOME%"
  )
  echo.
  echo Please go to
  echo   http://www.oracle.com/technetwork/java/javase/downloads/index.html
  echo and download a valid Java JDK and install before running Activator.
  echo.
  echo If you think this message is in error, please check
  echo your environment variables to see if "java" is
  echo available via JAVA_HOME or JRE_HOME.
  echo.
  goto :end
)

rem Check what Java version is being used to determine what memory options to use
for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
    set _JAVA_VERSION=%%g
)

rem Strips away the " characters
set _JAVA_VERSION=%_JAVA_VERSION:"=%
for /f "delims=. tokens=1-3" %%v in ("%_JAVA_VERSION%") do (
    set _MAJOR=%%v
    set _MINOR=%%w
    set _BUILD=%%x
 )

if "%_MINOR%" LSS "7" (
  echo You must use JRE/JDK 7+. A back port is in the road map.
  goto :end
)

REM ************************ set AUDIT_REACTIVE_HOME & FRAMEWORKS_HOME
for %%A in ("%_JRE_HOME%") do set _JRE_HOME=%%~sA
if "%_AUDIT_REACTIVE_HOME%"=="" set _AUDIT_REACTIVE_HOME=%~dp0..
for %%A in ("%_AUDIT_REACTIVE_HOME%") do set _AUDIT_REACTIVE_HOME=%%~sA
if not exist "%_AUDIT_REACTIVE_HOME%\lib\aspectjweaver.jar" (
    echo ERROR: Set the environment variable AUDIT_REACTIVE_HOME to the audit reactive home directory.
    goto :end
)
if "%_FRAMEWORKS_HOME%"=="" set "_FRAMEWORKS_HOME=%_AUDIT_REACTIVE_HOME%\etc"
for %%A in ("%_FRAMEWORKS_HOME%") do set _FRAMEWORKS_HOME=%%~sA

rem FIXME setlocal

REM ************************ Parse cmd line
set _SILENT=
set _DEBUG=
set _FRAMEWORK=
set _CONF=
set AUDIT_OPTS=
:parse
if "%1"=="-s" (
    set _SILENT=true
    shift
    goto :parse
)
if "%1"=="-d" (
    set _DEBUG=true
    shift
    goto :parse
)
if "%1"=="-sd" (
    set _SILENT=true
    set _DEBUG=true
    shift
    goto :parse
)
if "%1"=="-ds" (
    set _SILENT=true
    set _DEBUG=true
    shift
    goto :parse
)
if not "%1"=="" (
    set _FRAMEWORK=%1
    set _CONF=-DauditReactive=%_FRAMEWORKS_HOME%\%1.properties
    shift
    goto :parse
)

REM ************************ Set parameters
set _WEAVER=-javaagent:%_AUDIT_REACTIVE_HOME%\lib\aspectjweaver.jar
@REM Add audit agent with bootclasspath
Rem strategy with Xbootclasspath
rem set _XBOOT=-Xbootclasspath/p:%_AUDIT_REACTIVE_HOME%\lib\aspectjweaver.jar;%_AUDIT_REACTIVE_HOME%\lib\audit-reactive.jar;%_AUDIT_REACTIVE_HOME%\lib\audit-reactive-lib.jar
rem set AUDIT_OPTS=%_CONF% %_WEAVER% %_XBOOT%

@REM Add audit agent with java.ext.dirs
if exist "%_JAVA_HOME%\jre\lib\ext" (
set _EXT=%_JAVA_HOME%\jre\lib\ext
goto :extdir
)
if exist "%_JAVA_HOME%\lib\ext" (
set _EXT=%_JAVA_HOME%\lib\ext
goto :extdir
)
echo Not found JAVA_HOME/lib/ext >&2
goto :end

:extdir
set _EXTDIR=-Djava.ext.dirs=%_EXT%;%_AUDIT_REACTIVE_HOME%\lib
set AUDIT_OPTS=%_CONF% %_WEAVER% %_EXTDIR%

if "%_DEBUG%"=="true" (
    echo AUDIT_REACTIVE_HOME = %AUDIT_REACTIVE_HOME%
    echo FRAMEWORKS_HOME     = %_FRAMEWORKS_HOME%
    echo JRE_HOME            = %_JRE_HOME%
    echo EXT                 = %_EXT%
    echo JAVACMD             = %_JAVACMD%
    echo.
    echo AUDIT_OPTS=%AUDIT_OPTS%
    echo.
)

if "%_FRAMEWORK%"=="" (
    if not "%_SILENT%"=="true" (
        echo Now you can use the environment variable AUDIT_OPTS for launch Java program.
        echo.  set JAVA_OPTS=%%AUDIT_OPTS%%
        echo Add -DauditReactive=^<yourfile^>.properties to select a specific configuration.
    )
)

rem    set AUDIT_OPTS=%AUDIT_OPTS% -Djava.util.logging.manager=org.jboss.logmanager.LogManager
if "%_FRAMEWORK%" == "jboss" (
    REM See https://wiki.eclipse.org/LTWJboss7
    echo Not yet implemented
    goto :end
rem CF: https://wiki.eclipse.org/LTWJboss7
rem CF: https://www.eclipse.org/aspectj/doc/next/devguide/ltw-configuration.html
rem    set AUDIT_OPTS=%WEAVER%
    rem set AUDIT_OPTS=-Djboss.modules.system.pkgs=com.octo.reactive.audit,org.aspectj
    rem set AUDIT_OPTS=%AUDIT_OPTS% -Djboss.modules.system.pkgs=com.octo.reactive.audit,org.aspectj
    rem set AUDIT_OPTS=%AUDIT_OPTS% -Dorg.aspectj.tracing.factory=default
    rem set AUDIT_OPTS=%AUDIT_OPTS% -Dorg.aspectj.tracing.enabled=false
    rem set AUDIT_OPTS=%AUDIT_OPTS% -Dorg.aspectj.weaver.loadtime.configuration=com/octo/reactive/aop.xml
    rem set AUDIT_OPTS=%AUDIT_OPTS% -Dorg.aspectj.weaver.loadtime.configuration=file:///C:/Users/pprados/workspace.intellij/audit-reactive/src/main/resources/com/octo/reactive/aop.xml


    if not "%_SILENT%"=="true" (
        setlocal enabledelayedexpansion
        set dblpercent=%%%%
        echo In file ^"standalone.conf.bat^", add
        echo.  set JAVA_OPTS=%%JAVA_OPTS%%;%%AUDIT_OPTS%%
        echo "then you can start 'standalone'."
    )
)

rem if "%_FRAMEWORK%" == "glassfish" (
rem     if not "%_SILENT%"=="true" (
rem         echo "In the Administration Console open Configuration » JVM Settings » JVM Options"
rem         echo "Add:"
rem         echo.-DauditReactive=${AUDIT_REACTIVE_HOME}\etc\glassfish.properties
rem         echo.-Xbootclasspath/p:${AUDIT_REACTIVE_HOME}\lib\audit-reactive.jar;${AUDIT_REACTIVE_HOME}\lib\audit-reactive-lib.jar
rem         echo "and restart the domain."
rem     )
rem )

rem if "%_FRAMEWORK%" == "geronimo" (
rem     echo "Not implemented (Java 8)"
rem     goto :end
rem     set GERONIMO_OPTS=%AUDIT_OPTS%
rem     if not "%_SILENT%"=="true" (
rem        echo "GERONIMO_OPTS was set. You can use 'geronimo run'."
rem    )
rem )
rem if "%F_RAMEWORK%" == "websphere" (
rem     set JVM_ARGS=%AUDIT_OPTS%
rem     if not "%_SILENT%"=="true" (
rem         echo JVM_ARGS was set. You can use 'server run ^<server-name^>'.
rem     )
rem )

if "%_FRAMEWORK%" == "play" (
    set SBT_OPTS=%AUDIT_OPTS%
    if not "%_SILENT%"=="true" (
        echo SBT_OPTS was set. You can use TypeSafe 'activator run'.
    )
)
if "%_FRAMEWORK%" == "ant" (
    set ANT_OPTS=%AUDIT_OPTS%
    if not "%_SILENT%"=="true" (
        echo ANT_OPTS was set. You can use 'ant'.
    )
)
if "%_FRAMEWORK%" == "sbt" (
    set SBT_OPTS=%AUDIT_OPTS%
    if not "%_SILENT%"=="true" (
        echo SBT_OPTS was set. You can use TypeSafe 'activator run'.
    )
)
if "%_FRAMEWORK%" == "maven" (
    set MAVEN_OPTS=%AUDIT_OPTS%
    if not "%_SILENT%"=="true" (
        echo MAVEN_OPTS was set. You can use 'mvn'.
    )
)
if "%_FRAMEWORK%" == "gradle" (
    set GRADLE_OPTS=%AUDIT_OPTS%
    if not "%_SILENT%"=="true" (
        echo GRADLE_OPTS was set. You can use 'gradle'.
    )
)
if "%_FRAMEWORK%" == "jetty" (
    if not "%_SILENT%"=="true" (
        echo To start Jetty, use: java %%AUDIT_OPTS%% -jar start.jar
    )
)

if "%_FRAMEWORK%" == "catalina" (
    set CATALINA_OPTS=%AUDIT_OPTS%
    if not "%_SILENT%"=="true" (
        echo CATALINA_OPTS was set. You can use 'catalina run'.
    )
)

)

goto :end

:help
    echo.
    echo Usage init-audit-reactive [options] [framework]
    echo.
    echo Framework:
    echo ^<nothing^>  Set AUDIT_OPTS
    echo jetty      Set AUDIT_OPTS to start jetty
    echo catalina   Set CATALINA_OPTS
    echo play       Set SBT_OPTS
    echo.
    echo ant        Set ANT_OPTS
    echo gradle     Set GRADLE_OPTS
    echo maven      Set MAVEN_OPTS
    echo sbt        Set SBT_OPTS
    echo.
    echo help       Print this message
    echo.
    echo Options:
    echo -s Silent mode
    echo -d Debug mode. Print values
    echo.
    echo Environment variables ^(read from context^):
    echo AUDIT_REACTIVE_HOME  Env. variable, if unset uses the home directory of this batch.
    echo FRAMEWORKS_HOME      Env. variable, if unset uses AUDIT_REACTIVE_HOME/etc
    echo.
    echo JRE_HOME             Env. variable, if unset detect the value with PATH
    echo JAVA_HOME            Env. variable, if unset detect the value with PATH
    echo JAVACMD              Env. variable, if unset use "java"
    echo.
    goto :end

:end
Rem clean env
echo off
set _SILENT=
set _DEBUG=
set _FRAMEWORK=
set _WEAVER=
set _XBOOT=
set _EXT=
set _EXTDIR=
set _JAVAOK=
set _JRE_HOME=
set _AUDIT_REACTIVE_HOME=
set _JAVA_VERSION=
set _JAVA_HOME=
set _JAVACMD=
set _MAJOR=
set _MINOR=
set _BUILD=
set _FRAMEWORKS_HOME=
