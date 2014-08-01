# Introduction
This audit tool aims to provide help to the use of *[Reactive architecture](http://www.reactivemanifesto.org/)* in project implementations.
For reminder, when applying this approach the application must use only **non-blocking APIs** and,
as soon as possible, return the current thread to a pool, limited by the number of CPU cores.
The code must also use Java 8 concurrency utility classes `Future<>` and `CompletableFuture<>` everywhere.

**WARNING:** This is an alpha version, works only with JRE 8 !

Now, 517 blocking methods was detected.

# How it works
To detect where the application uses a blocking API, this tool injects some
checks using a JVM agent (using [Aspectj](https://www.eclipse.org/aspectj/)).
We choose to use the load-time-wearing in order to not modify the compiled code.
Thus, it is easier to add or remove the audit.

The agent tries to detect all the invocations of blocking APIs *at runtime*.
It is not possible to detect them *at compile time*, because it is
depending on the specific running instance. For example, the
`Writer.write(..)` can be used for a byte array in memory
or for a socket.

Some threads can invoke a blocking API, others can not. It is possible
to select for which thread the agent must detect a call to a blocking API.

At the application startup, it is common to use some blocking API to
load parameters from file, etc. Therefore, it is possible to shift the
audit start time to a few seconds after the application startup.

Some blocking APIs are used to manage files. If the file system uses a SSD,
the latency is low. But if the file system is on a NAS or on the Cloud,
the latency is important. Therefore, it is possible to select the
acceptable level of latency for all the file API. By default, only the
medium and high file latency API generates an alert, because it is not possible
blocking API.

# Usage
To set the environment variables, use `bin/init-audit-reactive`.
This will set the variables `AUDIT_REACTIVE_HOME` to the reactive audit tool home directory
and `AUDIT_OPTS` to all the parameters to start a JVM.

_Note: The application startup can be *SLOW*. Each class to load must be inspected
to *detect* and *inject* each audits rules._

You can add a framework name as a parameter of this command, such as
`catalina`, `jetty` or `play`.

To start **JVM** with the audit on Windows:

    > init-audit-reactive
    > java %AUDIT_OPTS% ...

To start **JVM** with the audit on Linux:

    > . init-audit-reactive
    > java %AUDIT_OPTS% ...

To start **jetty** with the audit on Windows:

    > init-audit-reactive jetty
    > java %AUDIT_OPTS% -jar start.jar

To start **jetty** with the audit on Linux:

    > . init-audit-reactive jetty
    > java %AUDIT_OPTS% -jar start.jar

To start **catalina** (Tomcat) with audit on Windows:

    > init-audit-reactive catalina
    > catalina run

To start **catalina** (Tomcat) with audit on Linux:

    > . init-audit-reactive catalina
    > catalina run

To start **play** with the audit on Windows:

    > init-audit-reactive play
    > activator run

To start **play** with the audit on Linux:

    >. init-audit-reactive play
    > activator run

For the background, this script add a boot classpath with `<audit home>/lib`
and adds the agent using `-javaagent:<audit home>/lib/aspectjweaver.jar`.

If a framework is selected, this script adds a default associated parameter file
with `-DauditReactive=<audit home>/etc/<framework>.properties`.
Sometime, the specific environment variable is set to start the framework.

# Parameters
All the parameters are named using the pattern `auditReactive.<key>`.
To set the parameters, you can use:

* an environment variable (`set auditReactive.file=low`)
* a properties file (`auditReactive.file=low`)
* or the java system properties (`java -DauditReactive.file=low ...`)

The values are read in the latter order.

The filename of parameters file is by default: `auditReactive.properties`
To use another file, set the variable `auditReactive`:

    java -DauditReactive=config.properties ...
    
or

    set auditReactive=config.properties
    java ...

You can set all the parameters described in `<audit home>/etc/default.properties`.

# Compile
use
    gradlew build

