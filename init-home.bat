@Rem
@Rem Tools to simulate an installation in ./home with some "soft link"
@Rem Compile quickly with gradlew jar
@echo off
call gradlew instDist
@echo off
setlocal
set "HOME=%~dp0"
For /F "tokens=1* delims==" %%A IN (%HOME%\reactive-audit-agent\build\install\reactive-audit-agent\bin\version.properties) DO (
    IF "%%A"=="version" set VERSION=%%B
    )

set VERSION=%VERSION:'=%

REM Reset
if exist %HOME%\home\etc (
  rmdir %HOME%\home\etc
  rmdir /S /Q %HOME%\home
)

mkdir %HOME%\home\bin
mkdir %HOME%\home\lib

pushd %HOME%\home\bin
mklink debug-reactive-audit.bat ..\..\reactive-audit-agent\src\test\bin\debug-reactive-audit.bat
mklink debug-reactive-audit  ..\..\reactive-audit-agent\src\test\bin\debug-reactive-audit
mklink version.properties       ..\..\reactive-audit-agent\src\main\resources\version.properties
mklink reactive-audit.bat  ..\..\reactive-audit-agent\src\main\dist\bin\reactive-audit.bat
mklink reactive-audit   ..\..\reactive-audit-agent\src\main\dist\bin\reactive-audit
popd

pushd %HOME%\home
mklink /D etc                   ..\reactive-audit-agent\src\main\dist\etc
popd

pushd %HOME%\home\lib
echo on
copy ..\..\reactive-audit-agent\build\install\reactive-audit-agent\lib\aspectjweaver.jar aspectjweaver.jar
mklink reactive-audit-lib.jar   ..\..\reactive-audit-lib\build\libs\reactive-audit-lib-%VERSION%.jar
mklink reactive-audit-agent.jar ..\..\reactive-audit-agent\build\libs\reactive-audit-agent-%VERSION%.jar
popd

