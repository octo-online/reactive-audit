# Introduction
This audit tool aims to provide help to the use of *[Reactive architecture](http://www.reactivemanifesto.org/)* in project implementations.
For reminder, when applying this approach the application must use only **non-blocking APIs** and,
as soon as possible, return the current thread to a pool, limited by the number of CPU cores.
The code must also use Java 8 concurrency utility classes `Future<>` and `CompletableFuture<>` everywhere.
The frameworks [Play](https://www.playframework.com/ "Play framework"), [AKKA](http://www.akka.io/ "AKKA framework")
and [Scala](http://www.scala-lang.org/ "Scala lang") promote this approach.

**WARNING:** This is a beta version

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
(parameter `reactiveAudit.threadPattern`).

At the application startup, it is common to use some blocking APIs to
load parameters from a file, etc. Therefore, it is possible to shift the
audit start time to a few seconds after the application startup
(parameter `reactiveAudit.bootstrapDelay`).

Some blocking APIs are used to manage files. If the file system uses a SSD,
the latency is low. But if the file system is on a NAS or on the Cloud,
the latency is high. Therefore, it is possible to select the
acceptable level of latency for all the file API. By default, only the
medium and high latency file APIs generate an alert.

# Usage
To set the environment variables, use `bin/init-reactive-audit`.
This will set the variable `REACTIVE_AUDIT_HOME` to the reactive audit tool home directory
and `AUDIT_OPTS` with the parameters needed to start a JVM.

_Note: The application startup can be *SLOW*. Each class to load must be inspected
to *detect* and *inject* each audits rules._

You can add a framework or server name as a parameter of this command, such as
`catalina`, `jetty` or `play` in order to apply a pre-defined configuration.

To start **JVM** with the audit on *Windows*:

    > init-reactive-audit
    > java %AUDIT_OPTS% ...

To start **JVM** with the audit on *Mac/Linux*:

    > source init-reactive-audit.sh
    > java %AUDIT_OPTS% ...

To start **jetty** with the audit on *Windows*:

    > init-reactive-audit jetty
    > java %AUDIT_OPTS% -jar start.jar

To start **jetty** with the audit on *Mac/Linux*:

    > source init-reactive-audit.sh jetty
    > java %AUDIT_OPTS% -jar start.jar

To start **catalina** (Tomcat) with audit on *Windows*:

    > init-reactive-audit catalina
    > catalina run

To start **catalina** (Tomcat) with audit on *Mac/Linux*:

    > source init-reactive-audit.sh catalina
    > catalina run

To start **play** with the audit on *Windows*:

    > init-reactive-audit play
    > activator run

To start **play** with the audit on *Mac/Linux*:

    >source init-reactive-audit.sh play
    > activator run

For the background, this script append the `java.ext.dir` with `<audit home>/lib`
and adds the agent using `-javaagent:<audit home>/lib/aspectjweaver.jar`.

If a framework is selected, this script adds the pre-defined associated parameter file
with `-DreactiveAudit=<audit home>/etc/<framework>.properties`.
Sometimes other specific environment variables are set to start the framework.

# Parameters
All the parameters are named using the pattern `reactiveAudit.<key>`.
To set the parameters, you can use:

* environment variable (`export reactiveAudit_file=low`)
* a properties file (`reactiveAudit.file=low`)
* java system properties (`java -DreactiveAudit.file=low ...`)

The values are read in the latter order.

The filename of parameters file is by default: `reactiveAudit.properties`
To use another file, set the variable `reactiveAudit`:

    java -DreactiveAudit=config.properties ...
    
or

    set reactiveAudit=config.properties
    java ...

You can set all the parameters described in `<audit home>/etc/default.properties`.

# Build
    ./gradlew usage

## Unit test can be compiled only with Java 8+.

    ./gradlew build (with Jdk8+)
    ./gradlew build -x test (with Jdk7)

## Distribution
use one of

    ./gradlew distZip
    ./gradlew distTar

## Use IntelliJ
Init projets
    ./gradlew idea

## Use Eclipse
Init projets
    ./gradlew eclipse

## Test distribution (Option 1)
Add `./build/install/reactive-audit/bin` in `PATH`, and

    ./gradlew installDist


## Test distribution (Option 2)
Then, add `./home/bin` in `PATH`, and

    ./init-home.sh (simulate installation with links)
    
To build more quickly

    ./gradlew build

