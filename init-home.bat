@Rem
@Rem Tools to simulate an installation in ./home with some "soft link"
@echo off
call gradlew instDist
@echo off
setlocal
set "HOME=%~dp0"
For /F "tokens=1* delims==" %%A IN (%HOME%\audit-reactive-agent\build\install\audit-reactive-agent\bin\version.properties) DO (
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
mklink debug-audit-reactive.bat ..\..\audit-reactive-agent\src\test\bin\debug-audit-reactive.bat
mklink debug-audit-reactive.sh  ..\..\audit-reactive-agent\src\test\bin\debug-audit-reactive.sh
mklink version.properties       ..\..\audit-reactive-agent\src\main\resources\version.properties
mklink init-audit-reactive.bat  ..\..\audit-reactive-agent\src\main\dist\bin\init-audit-reactive.bat
mklink init-audit-reactive.sh   ..\..\audit-reactive-agent\src\main\dist\bin\init-audit-reactive.sh
popd

pushd %HOME%\home
mklink /D etc                   ..\audit-reactive-agent\src\main\dist\etc
popd

pushd %HOME%\home\lib
echo on
copy ..\..\audit-reactive-agent\build\install\audit-reactive-agent\lib\aspectjweaver.jar aspectjweaver.jar
mklink audit-reactive-lib.jar   ..\..\audit-reactive-lib\build\libs\audit-reactive-lib-%VERSION%.jar
mklink audit-reactive-agent.jar ..\..\audit-reactive-agent\build\libs\audit-reactive-agent-%VERSION%.jar
popd

