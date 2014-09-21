#!/bin/bash
#
# Tools to simulate an installation in ./home with some "soft link"

./gradlew instDist

source "${AUDIT_REACTIVE_HOME}/audit-reactive-agent/bin/version.properties"

declare AUDIT_REACTIVE_HOME="$(dirname  "$0")"
if [[ -e "$AUDIT_REACTIVE_HOME/home" ]]; then
  rm -Rf "$AUDIT_REACTIVE_HOME/home"
fi

mkdir -p "$AUDIT_REACTIVE_HOME/home/bin"
mkdir -p "$AUDIT_REACTIVE_HOME/home/lib"

pushd "$AUDIT_REACTIVE_HOME/home/bin" >/dev/null
ln -s ../../audit-reactive-lib/src/test/bin/debug-audit-reactive.bat debug-audit-reactive.bat
ln -s ../../audit-reactive-lib/src/test/bin/debug-audit-reactive.sh  debug-audit-reactive.sh
ln -s ../../audit-reactive-lib/src/main/resources/version.properties version.properties
ln -s ../../audit-reactive-lib/src/main/dist/bin/init-audit-reactive.bat  init-audit-reactive.bat
ln -s ../../audit-reactive-lib/src/main/dist/bin/init-audit-reactive.sh   init-audit-reactive.sh
popd >/dev/null

pushd "$AUDIT_REACTIVE_HOME/home" >/dev/null
ln -s ../audit-reactive-lib/src/main/dist/etc etc
popd >/dev/null

pushd "$AUDIT_REACTIVE_HOME/home/lib" >/dev/null
# not ln -s
cp    ../../audit-reactive-lib/build/install/audit-reactive-agent/lib/aspectjweaver.jar aspectjweaver.jar
ln -s ../../audit-reactive-lib/build/libs/audit-reactive-lib-${version}.jar       audit-reactive-lib.jar
ln -s ../../audit-reactive-lib/build/libs/audit-reactive-${version}.jar           audit-reactive.jar
popd >/dev/null

