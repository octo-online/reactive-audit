# Introduction
<<<<<<< HEAD
This audit tool would like to help the usage of *[Reactive architecture](http://www.reactivemanifesto.org/)*.
With this approach, the application must use only a **non-blocking API** and
as soon as possible, return the current thread to a pool, limited by the number of CPU core.
The code use `Future<>` and `CompletableFuture<>` everywhere.

**WARNING:** This is an alpha version, works only with JRE 8 !

# How there work
To detect where the application use a blocking API, this tool inject some
checks with a JVM agent (Use [Aspectj](https://www.eclipse.org/aspectj/)).
We choose to use the load-time-wearing to not modify the compiled code.
Then, it's easier to add or remove the audit.

The agent try to detect all the invocation of blocking API *at runtime*.
It's not possible to detect *at compile time*, because it's depend of the specific
instance. By example, the `Writer.write(..)` can be use for a byte array in memory
or for a socket.

Some threads can invoke the blocking API, other can not. It's possible
to select witch thread the agent must detect a call to a blocking API.
=======
This audit tool aims to provide help to the use of *[Reactive architecture](http://www.reactivemanifesto.org/)* in project implementations.
For reminder, when applying this approach the application must use only **non-blocking APIs** and,
as soon as possible, return the current thread to a pool, limited by the number of CPU cores.
The code msut also use Java 8 concurrency utility classes `Future<>` and `CompletableFuture<>` everywhere.

# How it works
To detect where the application uses a blocking API, this tool injects some
checks using a JVM agent (using [Aspectj](https://www.eclipse.org/aspectj/)).
We choose to use the load-time-wearing in ordder to not modify the compiled code.
Thus, it is easier to add or remove the audit.

The agent tries to detect all the invocations of blocking APIs *at runtime*.
It is not possible to detect them *at compile time*, because it is depending on the specific running
instance. For example, the `Writer.write(..)` can be used for a byte array in memory
or for a socket.

Some threads can invoke a blocking API, others can not. It is possible
to select for which thread the agent must detect a call to a blocking API.
>>>>>>> Some readme.md file corrections.

At the application startup, it is common to use some blocking API to load parameters from file, etc.
Therefore, it is possible to shift the audit start time to a few seconds after the application startup.

<<<<<<< HEAD
Some blocking API are for manage files. If the file system use SSD
the latency is low. But if the file system is on NAS or Cloud, the latency is important.
So, it's possible to select the level of latency acceptable for all file API.
By default, only the medium and high latency for file API generate an alert, because it's not possible
to propose an alternative for the low latency file API.
=======
Some blocking APIs are used to manage files. If the file system uses a SSD,
the latency is low. But if the file system is on a NAS or on the Cloud, the latency is important.
Therefore, it is possible to select the acceptable level of latency for all the file API.
By default, only the medium and high file latency API generates an alert, because it is not possible
to suggest an alternative for the low file latency API.
>>>>>>> Some readme.md file corrections.

This is the same for the network API: it is possible to select the level for the network API.
By default, all the API can generate an alert.

<<<<<<< HEAD
Sometime, the code block due to the synchronize mechanism proposed by java.
By example, it's not a good idea to call `Thread.sleed(..)` in the thread
come from a thread pool. So, it's possible to select the level for CPU 
=======
Sometimes, the code blocks due to the synchronization mechanism proposed by java.
For example, it is not a good idea to call `Thread.sleep(..)` in a thread
coming from the thread pool. It is thus possible to select the level for the CPU 
>>>>>>> Some readme.md file corrections.
blocking API. 

# Usage
To set the environment variables, use `bin/init-audit-reactive`.
This will set the variables `AUDIT_REACTIVE_HOME` to the reactive audit tool home directory
and `AUDIT_OPTS` to all the parameters to start a JVM.

<<<<<<< HEAD
You can add some framework name like 'catalina', `jetty` or `play`.
=======
You can add a framework name as a parameter of this command, such as `jetty` or `play`.
>>>>>>> Some readme.md file corrections.

To start **jetty** with the audit:

    > set-audit-reactive jetty
    > java %AUDIT_OPTS% -jar start.jar

To start **catalina** (Tomcat) with audit:

    > set-audit-reactive catalina
    > catalina run

To start **play** with the audit:

    > set-audit-reactive play
    > activator run

<<<<<<< HEAD
In the background, this script extend the `java.ext.dirs` with `<audit home>/lib`
and add the Java agent with `-javaagent:<audit home>/lib/aspectjweaver.jar`.
=======
For the background, this script extends the `java.ext.dirs` with `<audit home>/lib`
and adds the agent using `-javaagent:<audit home>/lib/aspectjweaver.jar`.
>>>>>>> Some readme.md file corrections.

If a framework is selected, this script adds a default associated parameter file
with `-DauditReactive=<audit home>/etc/<framework>.properties`.
Sometime, the specific environment variable is set to start the framework.

# Parameters
All the parameters are named using the pattern `auditReactive.<key>`.
To set the parameters, you can use:

<<<<<<< HEAD
* Environment variable (`set auditReactive.file=low`)
* the java system properties (`java -DauditReactive.file=low ...`)
* or a properties file (`auditReactive.file=low`)
=======
* an environment variable (`set auditReactive.file=low`)
* a properties file (`auditReactive.file=low`)
* or the java system properties (`java -DauditReactive.file=low ...`)
>>>>>>> Some readme.md file corrections.

The values are read in the latter order.

The filename of parameters file is by default: `auditReactive.properties`
To use another file, set the variable `auditReactive`:

    java -DauditReactive=config.properties ...
    
or

    set auditReactive=config.properties

You can set all the parameters described in `<audit home>/etc/default.properties`.

