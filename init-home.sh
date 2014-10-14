#!/bin/bash
#
# Tools to simulate an installation in ./home with some "soft link"
# Compile quickly with gradlew jar

./gradlew instDist

declare REACTIVE_AUDIT_HOME="$(dirname  "$0")"
if [[ -e "$REACTIVE_AUDIT_HOME/home" ]]; then
  rm -Rf "$REACTIVE_AUDIT_HOME/home"
fi

mkdir -p "$REACTIVE_AUDIT_HOME/home/bin"
mkdir -p "$REACTIVE_AUDIT_HOME/home/lib"

source "${REACTIVE_AUDIT_HOME}/reactive-audit-agent/build/install/reactive-audit-agent/bin/version.properties"

pushd "$REACTIVE_AUDIT_HOME/home/bin" >/dev/null
ln -s ../../reactive-audit-agent/src/test/bin/debug-reactive-audit.bat debug-reactive-audit.bat
ln -s ../../reactive-audit-agent/src/test/bin/debug-reactive-audit  debug-reactive-audit
ln -s ../../reactive-audit-agent/src/main/resources/version.properties version.properties
ln -s ../../reactive-audit-agent/src/main/dist/bin/reactive-audit.bat  reactive-audit.bat
ln -s ../../reactive-audit-agent/src/main/dist/bin/reactive-audit   reactive-audit
popd >/dev/null

pushd "$REACTIVE_AUDIT_HOME/home" >/dev/null
ln -s ../reactive-audit-agent/src/main/dist/etc etc
popd >/dev/null

pushd "$REACTIVE_AUDIT_HOME/home/lib" >/dev/null
# not ln -s
cp    ../../reactive-audit-agent/build/install/reactive-audit-agent/lib/aspectjweaver.jar aspectjweaver.jar
ln -s ../../reactive-audit-lib/build/libs/reactive-audit-lib-${version}.jar               reactive-audit-lib.jar
ln -s ../../reactive-audit-agent/build/libs/reactive-audit-agent-${version}.jar           reactive-audit-agent.jar
popd >/dev/null

