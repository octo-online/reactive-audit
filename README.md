To set the environment variables, use set-audit-reactive.
This will set the variables AUDIT_REACTIVE_HOME with the home directory
and AUDIT_OPTS with all the parameters to start a JVM.

You can add a framework name like "jetty" or "play".

To start jetty with audit:
>set-audit-reactive jetty
>java %AUDIT_OPTS% -jar start.jar

To start play with audit:
>set-audit-reactive play
>activator run




