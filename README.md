# Introduction
This audit tool aims to provide help to the use of *[Reactive architecture](http://www.reactivemanifesto.org/)* in project implementations.
For reminder, when applying this approach the application must use only **non-blocking APIs** and,
as soon as possible, return the current thread to a pool, limited by the number of CPU cores.
The code must also use Java 8 concurrency utility classes `Future<>` and `CompletableFuture<>` everywhere.
The frameworks [Play](https://www.playframework.com/ "Play framework"), [AKKA](http://www.akka.io/ "AKKA framework")
and [Scala](http://www.scala-lang.org/ "Scala lang") promote this approach.

_**WARNING:** This is a beta version_

Now, 517 blocking methods are detected.

# How it works
To detect where the application uses a blocking API, this tool injects some
checks using a JVM agent (using [Aspectj](https://www.eclipse.org/aspectj/)).
We chose to use the load-time-weawing in order to not modify the compiled code.
Thus, it is easier to add or remove the audit.

The agent tries to detect all the invocations of blocking APIs *at runtime*.
It is not possible to detect them *at compile time*, because it 
depends on the specific running instance. For example, the
`Writer.write(..)` can be used for a byte array in memory
or for a socket.

Some threads can invoke a blocking API, others cannot. It is possible
to select for which thread the agent must detect a call to a blocking API
(parameter `reactiveAudit_threadPattern`).

At the application startup, it is common to use some blocking APIs to
load parameters from a file, etc. Therefore, it is possible to shift the
audit start time to a few seconds after the application startup
(parameter `reactiveAudit_bootstrapDelay`).

Some blocking APIs are used to manage files. If the file system uses a SSD,
the latency is low. But if the file system is on a NAS or on the Cloud,
the latency is high. Therefore, it is possible to select the
acceptable level of latency for all the file API. By default, only the
medium and high latency file APIs generate an alert.

# Download
Unzip [this file](https://oss.sonatype.org/content/groups/staging/com/octo/reactive/audit/reactive-audit-agent/0.7/reactive-audit-agent-0.7.zip)
and add the `<audit home>/bin` directory in `PATH`.

# Usage
To set the environment variables, use `bin/reactive-audit`.
This will set the variable `REACTIVE_AUDIT_HOME` to the reactive audit tool home directory
and `AUDIT_OPTS` with the parameters needed to start a JVM.

_Note: The application startup can be *SLOW*. Each class to load must be inspected
to *detect* and *inject* each audits rules._

You can add a framework or server name as a parameter of this command, such as
`catalina`, `jetty` or `play` in order to apply a pre-defined configuration.

To start **JVM** with the audit on *Windows*:

    > reactive-audit
    > java %AUDIT_OPTS% ...

To start **JVM** with the audit on *Mac/Linux*:

    > source reactive-audit.sh
    > java %AUDIT_OPTS% ...

To start **jetty** with the audit on *Windows*:

    > reactive-audit jetty
    > java %AUDIT_OPTS% -jar start.jar

To start **jetty** with the audit on *Mac/Linux*:

    > source reactive-audit.sh jetty
    > java %AUDIT_OPTS% -jar start.jar

To start **catalina** (Tomcat) with audit on *Windows*:

    > reactive-audit catalina
    > catalina run
    
or    

    > reactive-audit catalina -c catalina run
    
To start **catalina** (Tomcat) with audit on *Mac/Linux*:

    > source reactive-audit.sh catalina
    > catalina run
    
or

    > reactive-audit.sh catalina -c catalina run    

To start **play** with the audit on *Windows*:

    > reactive-audit play
    > activator run
    
or    

    > reactive-audit play -c activator run
    

To start **play** with the audit on *Mac/Linux*:

    > source reactive-audit.sh play
    > activator run
    
or    

    > reactive-audit.sh play -c activator run
    
To start **Vert.X** with the audit on *Windows*:

    > reactive-audit vertx
    > vertx run ...
    
or    

    > reactive-audit vertx -c vertx run ...
    

To start **Vert.X** with the audit on *Mac/Linux*:

    > source reactive-audit.sh vertx
    > vertx run ...
    
or    

    > reactive-audit.sh vertx -c vertx run ...
    

For the background, this script append the `java.ext.dir` with `<audit home>/lib`
and adds the agent using `-javaagent:<audit home>/lib/aspectjweaver.jar`.

If a framework is selected, this script adds the pre-defined associated parameter file
with `-DreactiveAudit=<audit home>/etc/<framework>.properties`.
Sometimes other specific environment variables are set to start the framework.

# Parameters
All the parameters are named using the pattern `reactiveAudit_<key>`.
To set the parameters, you can use:

* environment variable (`export reactiveAudit_logOutput=console`)
* java system properties (`java -DreactiveAudit_file=low ...`)
* a properties file (`reactiveAudit_file=low`)

The values are read in the latter order.

The filename of parameters file is by default: `reactiveAudit_properties`
To use another file, set the variable `reactiveAudit`:

    java -DreactiveAudit=config.properties ...
    
or

    export reactiveAudit=config.properties
    java ...

You can set all the parameters described in `<audit home>/etc/default.properties`.

# Contribute

## Get sources
    git clone --recursive https://github.com/octo-online/reactive-audit.git

## Use IntelliJ
Init projects

    ./gradlew idea

## Use Eclipse
Init projects

    ./gradlew eclipse

## Compile

    ./gradlew usage

## Distribution
use one of

    ./gradlew distZip
    ./gradlew distTar

## Test distribution
Add `./build/install/reactive-audit/bin` in `PATH`, and

    ./gradlew installDist
