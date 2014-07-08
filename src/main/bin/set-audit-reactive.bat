@REM set-audit-reactive init script
@REM
@REM Environment:
@REM AUDIT_REACTIVE_HOME - location of a Audit Reactive home dir (optional)
@REM
@REM Parameters:
@REM -s          : Silent mode
@REM <framework> : Framework name. Must be "jetty" or "play"
@REM
@REM Set:
@REM AUDIT_OPTS  - JVM options for audit
@REM JAVA_OPTS : If <framework> is Play
@REM
@echo off
if "%AUDIT_REACTIVE_HOME%"=="" set "AUDIT_REACTIVE_HOME=%~dp0.."
if not exist %AUDIT_REACTIVE_HOME%\lib\aspectjweaver.jar (
    echo ERROR: Set the environment variable AUDIT_REACTIVE_HOME to the audit reactive home directory. >&2
    exit /b 1
)
if "%FRAMEWORK_HOME%"=="" set FRAMEWORK_HOME=%AUDIT_REACTIVE_HOME%\etc
set SILENT=
set FRAMEWORK=
set AUDIT_OPTS=
:parse
if "%1"=="-s" (
    set SILENT=true
    shift
    goto :parse
)
if not "%1"=="" (
    set FRAMEWORK=%1
    set AUDIT_OPTS=-DauditReactive="%FRAMEWORK_HOME%\%1.properties"
    shift
    goto :parse
)

set AUDIT_OPTS=-javaagent:"%AUDIT_REACTIVE_HOME%\lib\aspectjweaver.jar" -Djava.ext.dirs="%JAVA_HOME%\jre\lib\ext;%AUDIT_REACTIVE_HOME%\lib" %AUDIT_OPTS%
if not "%SILENT%"=="true" (
    echo Now you can use the environment variable AUDIT_OPTS for launch Java program.
    if "%FRAMEWORK%"=="" (
        echo Add -DauditReactive=[yourfile].properties to select a specific configuration.
    )
)
if "%FRAMEWORK%" == "play" (
    set SBT_OPTS=%AUDIT_OPTS%
    if not "%SILENT%"=="true" (
        echo SBT_OPTS was set. You can use TypeSafe activator.
    )
)
if "%FRAMEWORK%" == "jetty" (
    if not "%SILENT%"=="true" (
        echo "Use: java %%AUDIT_OPTS%% -jar start.jar
    )
)
set AUDIT_OPTS=
set SILENT=
set FRAMEWORK=
