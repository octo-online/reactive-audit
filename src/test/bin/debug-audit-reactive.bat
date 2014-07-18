@REM use the framework properties files from src/test/etc
@echo off
set "FRAMEWORKS_HOME=%~dp0..\..\src\test\etc"
%AUDIT_REACTIVE_HOME%\bin\init-audit-reactive.bat %*
