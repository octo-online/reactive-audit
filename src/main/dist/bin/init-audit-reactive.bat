@REM init-audit-reactive script
@REM
@REM Environment:
@REM AUDIT_REACTIVE_HOME - location of a Audit Reactive home dir (optional)
@REM FRAMEWORKS_HOME - The etc/ directory with the default properties for frameworks (optional)
@REM
@REM Parameters:
@REM -s          : Silent mode (optional)
@REM <framework> : Framework name. Must be "jetty", "catalina", "glassfish" or "play". (optional)
@REM
@REM Set:
@REM AUDIT_OPTS  - JVM options for audit
@REM *_OPTS      - If <framework> would like.
@REM
@REM same approach : http://manuals.zeroturnaround.com/jrebel/standalone/launch-from-command-line.html
@echo off
goto :ok
rem FIXME
VERIFY OTHER 2>nul
setlocal ENABLEEXTENSIONS
if ERRORLEVEL 0 goto ok
echo "Unable to enable extensions"
exit /B 1
:ok

if "%AUDIT_REACTIVE_HOME%"=="" set "AUDIT_REACTIVE_HOME=%~dp0.."
for %%A in ("%AUDIT_REACTIVE_HOME%") do set HOME=%%~sA
if not exist %HOME%\lib\aspectjweaver.jar (
    echo ERROR: Set the environment variable AUDIT_REACTIVE_HOME to the audit reactive home directory. >&2
    exit /b 1
)
if "%FRAMEWORKS_HOME%"=="" set "FRAMEWORKS_HOME=%HOME%\etc"

rem FIXME setlocal

REM Manage the space in file name
for %%A in ("%JAVA_HOME%") do set SJAVA_HOME=%%~sA
for %%A in ("%FRAMEWORKS_HOME%") do set SFRAMEWORKS_HOME=%%~sA

rem set AS_JAVA=

set SILENT=
set DEBUG=
set FRAMEWORK=
set CONF=
set AUDIT_OPTS=
:parse
if "%1"=="-s" (
    set SILENT=true
    shift
    goto :parse
)
if "%1"=="-d" (
    set DEBUG=true
    shift
    goto :parse
)
if "%1"=="-sd" (
    set SILENT=true
    set DEBUG=true
    shift
    goto :parse
)
if "%1"=="-ds" (
    set SILENT=true
    set DEBUG=true
    shift
    goto :parse
)
if not "%1"=="" (
    set FRAMEWORK=%1
    set CONF=-DauditReactive=%SFRAMEWORKS_HOME%\%1.properties
    shift
    goto :parse
)

REM FIXME : verifier JAVA_HOME si -Djava.ext.dirs

set WEAVER=-javaagent:%HOME%\lib\aspectjweaver.jar
@REM Add audit agent with bootclasspath
set XBOOT=-Xbootclasspath/p:%HOME%\lib\aspectjweaver.jar;%HOME%\lib\audit-reactive.jar;%HOME%\lib\audit-reactive-lib.jar
set AUDIT_OPTS=%AUDIT_OPTS% %CONF% %WEAVER% %XBOOT%

@REM Add audit agent with java.ext.dirs
rem set EXTDIR=-Djava.ext.dirs=%SJAVA_HOME%\jre\lib\ext;%HOME%\lib
rem set AUDIT_OPTS=%AUDIT_OPTS% %CONF% %WEAVER% %EXTDIR%

if "%FRAMEWORK%"=="" (
    if not "%SILENT%"=="true" (
        echo Now you can use the environment variable AUDIT_OPTS for launch Java program.
        echo.  set JAVA_OPTS=%AUDIT_OPTS%
        echo Add -DauditReactive=^<yourfile^>.properties to select a specific configuration.
    )
)

rem    set AUDIT_OPTS=%AUDIT_OPTS% -Djava.util.logging.manager=org.jboss.logmanager.LogManager
if "%FRAMEWORK%" == "jboss" (
    REM See https://wiki.eclipse.org/LTWJboss7
    echo Not implemented
    goto :end
rem CF: https://wiki.eclipse.org/LTWJboss7
rem CF: https://www.eclipse.org/aspectj/doc/next/devguide/ltw-configuration.html
rem    set AUDIT_OPTS=%WEAVER%
    rem set AUDIT_OPTS=-Djboss.modules.system.pkgs=com.octo.reactive.audit,org.aspectj
    rem set AUDIT_OPTS=%AUDIT_OPTS% -Djboss.modules.system.pkgs=com.octo.reactive.audit,org.aspectj
    rem set AUDIT_OPTS=%AUDIT_OPTS% -Dorg.aspectj.tracing.factory=default
    rem set AUDIT_OPTS=%AUDIT_OPTS% -Dorg.aspectj.tracing.enabled=false
    rem set AUDIT_OPTS=%AUDIT_OPTS% -Dorg.aspectj.weaver.loadtime.configuration=com/octo/reactive/aop.xml
    rem set AUDIT_OPTS=%AUDIT_OPTS% -Dorg.aspectj.weaver.loadtime.configuration=file:///C:/Users/pprados/workspace.intellij/audit-reactive/src/main/resources/com/octo/reactive/aop.xml


    if not "%SILENT%"=="true" (
        echo In file ^"standalone.conf.bat^", add
        echo.  set JAVA_OPTS=%%JAVA_OPTS%% %%AUDIT_OPTS%%
        echo "then you can start 'standalone'."
    )
)

if "%FRAMEWORK%" == "glassfish" (
    if not "%SILENT%"=="true" (
        echo "In the Administration Console open Configuration » JVM Settings » JVM Options"
        echo "Add:"
        echo.-DauditReactive=${AUDIT_REACTIVE_HOME}\etc\glassfish.properties
        echo.-Xbootclasspath/p:${AUDIT_REACTIVE_HOME}\lib\audit-reactive.jar;${AUDIT_REACTIVE_HOME}\lib\audit-reactive-lib.jar
        echo "and restart the domain."
    )
)

if "%FRAMEWORK%" == "geronimo" (
    echo "Not implemented (Java 8)"
    exit /B 1
    set GERONIMO_OPTS=%AUDIT_OPTS%
    if not "%SILENT%"=="true" (
        echo "GERONIMO_OPTS was set. You can use 'geronimo run'."
    )
)
rem if "%FRAMEWORK%" == "websphere" (
rem     set JVM_ARGS=%AUDIT_OPTS%
rem     if not "%SILENT%"=="true" (
rem         echo JVM_ARGS was set. You can use 'server run ^<server-name^>'.
rem     )
rem )

if "%FRAMEWORK%" == "play" (
    set SBT_OPTS=%AUDIT_OPTS%
    if not "%SILENT%"=="true" (
        echo SBT_OPTS was set. You can use TypeSafe 'activator run'.
    )
)
if "%FRAMEWORK%" == "jetty" (
    if not "%SILENT%"=="true" (
        echo "To start Jetty, use: java %%AUDIT_OPTS%% -jar start.jar
    )
)

if "%FRAMEWORK%" == "catalina" (
    set CATALINA_OPTS=%AUDIT_OPTS%
    if not "%SILENT%"=="true" (
        echo CATALINA_OPTS was set. You can use 'catalina run'.
    )
)

)

if "%DEBUG%"=="true" (
    echo AUDIT_OPTS=%AUDIT_OPTS%
)
:end
set SILENT=
set DEBUG=
set FRAMEWORK=
set WEAVER=
set XBOOT=
set EXTDIR=
set SJAVA_HOME=
set SFRAMEWORKS_HOME=
set HOME=
