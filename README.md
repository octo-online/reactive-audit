# Introduction
This audit tool would like to help the usage of *[Reactive architecture](http://www.reactivemanifesto.org/)*.
With this approach, the application must use only a **non-blocking API** and
as soon as possible, return the current thread to a pool, limited by the number of CPU core.
The code use `Future<>` and `CompletableFuture<>` everywhere.

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
to select witch thread the agent must detect a call to a blocking api.

At startup, it's normal to use some blocking API to load parameters from file, etc.
It's possible to shift the audit after few seconds.

Some blocking API are for manage files. If the file system use SSD
the latency is low. But if the file system is on NAS or Cloud, the latency is important.
So, it's possible to select the level of latency acceptable for all file API.
By default, only the medium and high file API generate an alert, because it's not possible
to propose an alternative for the low file latency API.

It's the same for network API. It's possible to select the level for network API.
By default, all API can generate an alert.

Sometime, the code block due to the synchronize mechanism proposed by java.
By example, it's not a good idea to call `Thread.sleed(..)` in the thread
come from the thread pool. So, it's possible to select the level for CPU 
blocking API. 

# Usage
To set the environment variables, use `bin/init-audit-reactive`.
This will set the variables `AUDIT_REACTIVE_HOME` with the home directory
and `AUDIT_OPTS` with all the parameters to start a JVM.

You can add some framework name like `jetty` or `play`.

To start **jetty** with audit:

    >set-audit-reactive jetty
    >java %AUDIT_OPTS% -jar start.jar

To start **play** with audit:

    >set-audit-reactive play
    >activator run

In the background, this script extend the `java.ext.dirs` with `<audit home>/lib`
and add the agent with `-javaagent:<audit home>/lib/aspectjweaver.jar`.

If a framework is selected, this script add a default associated parameter file
with `-DauditReactive=<audit home>/etc/<framework>.properties`.

# Parameters
All parameters were in form `auditReactive.<key>`.
To set parameters, you can use:

* Environment variable (`set auditReactive.file=low`)
* a properties file (`auditReactive.file=low`)
* or the java system properties (`java -DauditReactive.file=low ...`)

The values are overflow in this order.

The filename with parameters is, by default : `auditReactive.properties`
To use another file, set the variable `auditReactive`.

    java -DauditReactive=config.properties ...
    
or

    set auditReactive=config.properties

You can set all the parameters describe in `<audit home>/etc/default.properties`.

