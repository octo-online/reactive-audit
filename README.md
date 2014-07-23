# Introduction
This audit tool aims to provide help to the use of *[Reactive architecture](http://www.reactivemanifesto.org/)* in project implementations.
For reminder, when applying this approach the application must use only **non-blocking APIs** and,
as soon as possible, return the current thread to a pool, limited by the number of CPU cores.
The code must also use Java 8 concurrency utility classes `Future<>` and `CompletableFuture<>` everywhere.

# How it works
To detect where the application uses a blocking API, this tool injects some
checks using a JVM agent (using [Aspectj](https://www.eclipse.org/aspectj/)).
We chose to use the load-time-weaving in order not to modify the compiled code.
Thus, it is easier to add or remove the audit.

The agent tries to detect all the invocations of blocking APIs *at runtime*.
It is not possible to detect them *at compile time*, because it is depending on the specific running
instance. For example, the `Writer.write(..)` can be used for a byte array in memory
or for a socket.

Some threads can invoke a blocking API, others cannot. It is possible
to select for which thread the agent must detect a call to a blocking API.

At the application startup, it is common to use some blocking API to load parameters from file, etc.
Therefore, it is possible to shift the audit start time to a few seconds after the application startup.

Some blocking APIs are used to manage files. If the file system uses a SSD,
the latency is low. But if the file system is on a NAS or on the Cloud, the latency is high.
Therefore, it is possible to select the acceptable level of latency for all the file API.
By default, only the medium and high file latency API generates an alert, because it is not possible
to suggest an alternative for the low file latency API.

This is the same for the network API: it is possible to select the level for the network API.
By default, all the API can generate an alert.

Sometimes, the code blocks due to the synchronization mechanism proposed by java.
For example, it is not a good idea to call `Thread.sleep(..)` in a thread
coming from the thread pool. It is thus possible to select the level for the CPU 
blocking API. 

# Usage
To set the environment variables, use `bin/init-audit-reactive`.
This will set the variables `AUDIT_REACTIVE_HOME` to the reactive audit tool home directory
and `AUDIT_OPTS` to all the parameters to start a JVM.

You can add a framework name as a parameter of this command, such as `jetty` or `play`.

To start **jetty** with the audit:

    >set-audit-reactive jetty
    >java %AUDIT_OPTS% -jar start.jar

To start **play** with the audit:

    >set-audit-reactive play
    >activator run

For the background, this script extends the `java.ext.dirs` with `<audit home>/lib`
and adds the agent using `-javaagent:<audit home>/lib/aspectjweaver.jar`.

If a framework is selected, this script adds a default associated parameter file
with `-DauditReactive=<audit home>/etc/<framework>.properties`.

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

You can set all the parameters described in `<audit home>/etc/default.properties`.

