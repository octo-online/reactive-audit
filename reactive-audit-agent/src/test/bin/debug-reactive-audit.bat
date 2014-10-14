@REM use the framework properties files from src/test/etc
@echo off
set _REACTIVE_AUDIT_HOME=%REACTIVE_AUDIT_HOME%
if "%_REACTIVE_AUDIT_HOME%"=="" set _REACTIVE_AUDIT_HOME=%~dp0..
set "FRAMEWORKS_HOME=%~dp0..\..\reactive-audit-agent\src\test\etc"
call %_REACTIVE_AUDIT_HOME%\bin\reactive-audit.bat %*
set FRAMEWORKS_HOME=
set _REACTIVE_AUDIT_HOME=