@Rem
@Rem Tools to simulate an installation in ./home with some "soft link"
@echo off
call gradlew instDist

setlocal
For /F "tokens=1* delims==" %%A IN (%HOME%\bin\version.properties) DO (
    IF "%%A"=="version" set VERSION=%%B
    )
set "HOME=%~dp0"

REM Reset
if exist %HOME%\home\etc (
  rmdir %HOME%\home\etc
  rmdir /S /Q %HOME%\home
)

mkdir %HOME%\home\bin
mkdir %HOME%\home\lib

pushd %HOME%\home\bin
mklink debug-audit-reactive.bat ..\..\src\test\bin\debug-audit-reactive.bat
mklink debug-audit-reactive.sh ..\..\src\test\bin\debug-audit-reactive.sh
mklink version.properties ..\..\src\main\resources\version.properties
mklink init-audit-reactive.bat ..\..\src\main\dist\bin\init-audit-reactive.bat
mklink init-audit-reactive.sh ..\..\src\main\dist\bin\init-audit-reactive.sh
popd

pushd %HOME%\home
mklink /D etc ..\src\main\dist\etc
popd

pushd %HOME%\home\lib
echo on
copy ..\..\build\install\audit-reactive\lib\aspectjweaver.jar aspectjweaver.jar
mklink audit-reactive-lib.jar ..\..\audit-reactive-lib\build\libs\audit-reactive-lib-%VERSION%.jar
mklink audit-reactive.jar ..\..\build\libs\audit-reactive-%VERSION%.jar
popd

