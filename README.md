set AUDIT_REACTIVE_HOME=<home>

JETTY
To start jetty with audit :

java -jar start.jar \
  --exec \
  -javaagent:%AUDIT_REACTIVE_HOME%/lib/aspectjweaver.jar \
  --lib=%AUDIT_REACTIVE_HOME%/lib/audit-reactive.jar \
  --lib=%AUDIT_REACTIVE_HOME%/lib/audit-reactive-lib.jar \
  -DauditReactive=%AUDIT_REACTIVE_HOME%/etc/auditJetty

Note: Do not add .properties for the parameter auditReactive. (See https://bugs.eclipse.org/bugs/show_bug.cgi?id=438989)

PLAY
To start play with audit :
Edit the sbt.bat. Add %ASPECTPATH%; in line
"%_JAVACMD%" %_JAVA_OPTS% %SBT_OPTS% -cp "%ASPECTPATH%;%SBT_HOME%sbt-launch.jar" xsbt.boot.Boot %*

Then set SBT_OPTS and ASPECTPATH.

rem set ASPECTJ_OPTS=-Dorg.aspectj.tracing.messages=true -Dorg.aspectj.verbose=true -Daj.weaving.verbose=true
set ASPECTPATH=\
  %AUDIT_REACTIVE_HOME%/lib/audit-reactive*.jar:\
  %AUDIT_REACTIVE_HOME%/lib/audit-reactive-lib*.jar
set SBT_OPTS=\
  -javaagent:%AUDIT_REACTIVE_HOME%/lib/aspectjweaver*.jar \
  -DauditReactive=%AUDIT_REACTIVE_HOME%/etc/auditPlay.properties

activator run


