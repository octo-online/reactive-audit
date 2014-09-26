@REM use the framework properties files from src/test/etc
@echo off
set "FRAMEWORKS_HOME=%~dp0..\..\audit-reactive-agent\src\test\etc"
call %AUDIT_REACTIVE_HOME%\bin\init-audit-reactive.bat %*
set FRAMEWORKS_HOME=