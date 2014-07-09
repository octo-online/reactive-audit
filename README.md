To set the environment variables, use `init-audit-reactive`.
This will set the variables `AUDIT_REACTIVE_HOME` with the home directory
and `AUDIT_OPTS` with all the parameters to start a JVM.

You can add a framework name like `jetty` or `play`.

To start jetty with audit:

    >set-audit-reactive jetty
    >java %AUDIT_OPTS% -jar start.jar

To start play with audit:

    >set-audit-reactive play
    >activator run

All parameters were in form `auditReactive.<key>`.
To set parameters, you can use:
- Environment variable

    > set auditReactive.file=low
    
- The java system properties

    > java -DauditReactive.file=low ...
    
- or a properties file

    auditReactive.file=low

The filename with parameters is, by default : `auditReactive.properties`
To use another file, set the variable auditReactive.

    >java -DauditReactive=config.properties ...
    
or

    > set auditReactive=config.properties

You can set all the parameters describe in `<home>/etc/default.properties`.

