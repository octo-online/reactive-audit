#!/bin/bash
#
# Tools to simulate an installation in ./home with some "soft link"

./gradlew instDist

VERSION=0.0.3-SNAPSHOT
declare AUDIT_REACTIVE_HOME="$(dirname  "$0")"
if [[ -z "$ASPECTJ_HOME" ]]; then
    echo 'Set $ASPECTJ_HOME'
    exit 1
fi

if [[ -e "$AUDIT_REACTIVE_HOME/home" ]]; then
  rm -Rf "$AUDIT_REACTIVE_HOME/home"
fi

mkdir -p "$AUDIT_REACTIVE_HOME/home/bin"
mkdir -p "$AUDIT_REACTIVE_HOME/home/lib"

pushd "$AUDIT_REACTIVE_HOME/home/bin" >/dev/null
ln -s ../../src/test/bin/debug-audit-reactive.bat debug-audit-reactive.bat 
ln -s  ../../src/test/bin/debug-audit-reactive.sh debug-audit-reactive.sh 
ln -s  ../../src/main/dist/bin/init-audit-reactive.bat init-audit-reactive.bat 
ln -s  ../../src/main/dist/bin/init-audit-reactive.sh init-audit-reactive.sh
popd >/dev/null

pushd "$AUDIT_REACTIVE_HOME/home" >/dev/null
ln -s ../src/main/dist/etc etc
popd >/dev/null

pushd "$AUDIT_REACTIVE_HOME/home/lib" >/dev/null
# not ln -s
ln "../../build/install/audit-reactive/lib/aspectjweaver.jar" aspectjweaver.jar
ln -s ../../audit-reactive-lib/build/libs/audit-reactive-lib-$VERSION.jar audit-reactive-lib.jar
ln -s "../../build/libs/audit-reactive-$VERSION.jar" audit-reactive.jar
popd >/dev/null

