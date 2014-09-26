@REM use the framework properties files from src/test/etc
@echo off
set "FRAMEWORKS_HOME=%~dp0..\..\reactive-audit-agent\src\test\etc"
call %REACTIVE_AUDIT_HOME%\bin\init-reactive-audit.bat %*
set FRAMEWORKS_HOME=