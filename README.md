# Introduction
This audit tool aims to provide help to the use of *[Reactive architecture](http://www.reactivemanifesto.org/)* in project implementations.
For reminder, when applying this approach the application must use only **non-blocking APIs** and,
as soon as possible, return the current thread to a pool, limited by the number of CPU cores.
The code must also use Java 8 concurrency utility classes `Future<>` and `CompletableFuture<>` everywhere.
The frameworks [Play](https://www.playframework.com/ "Play framework"), [AKKA](http://www.akka.io/ "AKKA framework"),
[Scala](http://www.scala-lang.org/ "Scala lang") or [Vert.x](http://vertx.io/) promote this approach.

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
To set the environment variables, after add `<audit home>/bin` in `PATH`, use `reactive-audit`.
This will set the variable `REACTIVE_AUDIT_HOME` to the reactive audit tool home directory
and `AUDIT_OPTS` with the parameters needed to start a JVM.

You can add a framework or server name as a parameter of this command, such as
`catalina`, `jetty`, `play`, etc. in order to apply a pre-defined configuration.

```sh-session
$ reactive-audit catalina
$ catalina run
```

The parameter `-d` is for debug.
The parameter `-s` is for silent.
At the end, the parameter `-c` can be use to chain with the framework.

_Note: The application startup can be *SLOW*. Each class to load must be inspected
to *detect* and *inject* each audits rules._


Framework | Windows | Mac/linux
:-- | --- | ---
unknown  | > reactive-audit<br>> java %AUDIT_OPTS% ...|$ source reactive-audit<br>$ java %AUDIT_OPTS% ... 
jetty    | > reactive-audit jetty<br>> java %AUDIT_OPTS% -jar start.jar  | $ source reactive-audit jetty<br>$ java %AUDIT_OPTS% -jar start.jar
catalina | > reactive-audit catalina -c catalina run | $ reactive-audit catalina -c catalina run
play     | > reactive-audit play -c activator run    | $ reactive-audit play -c activator run
vert.x   | > reactive-audit vertx -c vertx run ...   | $ reactive-audit vertx -c vertx run ...
maven    | > reactive-audit maven -c mvn ...         | $ reactive-audit maven -c mvn ...
gradle   | > reactive-audit gradle -c gradle ...     | $ reactive-audit gradle -c gradle ...
sbt      | > reactive-audit sbt -c sbt ...           | $ reactive-audit sbt -c sbt ...

For the background, this script append the `java.ext.dir` with `<audit home>/lib`
and adds the agent using `-javaagent:<audit home>/lib/aspectjweaver.jar`.

If a framework is selected, this script adds the pre-defined associated parameter file
with `-DreactiveAudit=<audit home>/etc/<framework>.properties`.
Sometimes other specific environment variables are set to start the framework.

# Using a build tool
Reactive-audit can be used with build tools that support Maven repositories.
See some samples with Maven, Gradle and SBT in the [integration project](https://github.com/octo-online/reactive-audit-integration)

# Annotations
With the `reactive-audit-lib.jar`, two annotations can be used.

`@TolerateLatency` force the JVM agent to tolerate a method call to a blocking API without log or exception.
All blocking call from this method or from one of its callees is accepted without generating an alert.

`@WithLatency` declare that a specific method has latency.
A call of this method can generate a log or throw a `ReactiveAuditException`
if the audit agent is used.

See [integration project](https://github.com/octo-online/reactive-audit-integration) for a sample.

# Parameters
All the parameters are named using the pattern `reactiveAudit_<key>`.
To set the parameters, you can use:

* environment variable (`export reactiveAudit_logOutput=console`)
* java system properties (`java -DreactiveAudit_file=low ...`)
* a properties file (`reactiveAudit_file=low`)

The values are read in the latter order.

The filename of parameters file is by default: `reactiveAudit_properties`
To use another file, set the variable `reactiveAudit`:

```sh-session
$ java -DreactiveAudit=config.properties ...
```       
or
```sh-session
$ export reactiveAudit=config.properties
$ java ...
```    

You can set all the parameters described in `<audit home>/etc/default.properties`.

For sample, to force the log to console without modify some file:
```sh-session
$ export reactiveAudit_logOutput=console
$ java ...
```    

# Contribute

We will be very happy if you can contribute. This king of tools must be tested with a lot of
contexts. May be, the default parameters for different frameworks are not enough ; 
you have a better idea to integrate this tool with other ; ...

## Get sources
```sh-session
$ git clone --recursive https://github.com/octo-online/reactive-audit.git
```    
## Build
Target| Command
:-- | --- | ---
Intellij | $ ./gradlew idea
Eclipse | $ ./gradlew eclipse
Compile | $ ./gradlew usage
Distribution | $ ./gradlew distZip<br>$ ./gradlew distTar<br>$ ./gradlew installDist
