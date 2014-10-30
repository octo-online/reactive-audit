# The annotation and exception was be used in audited application
With the `reactive-audit-lib.jar`, two annotations can be used.

`@TolerateLatency` force the JVM agent to tolerate a method call to a blocking API without log or exception.
All blocking call from this method or from one of its callees is accepted without generating an alert.

`@WithLatency` declare that a specific method has latency.
A call of this method can generate a log or throw a `ReactiveAuditException`
if the audit agent is used.

`@StartAudit` start the audit when this method is executed, if `reactiveAudit_bootstrapMode=ANNOTATION`.

See [integration project](https://github.com/octo-online/reactive-audit-integration) for a sample.

## History
Version | Comments
:-- | ---
v0.8 | Add @StartAudit annotation.<br>Add java.util.{Hashtable,Stack,Vector}<br>and java.util.concurrent.ConcurrentMap<br>Analyse the CPU to start the audit  
v0.7.1 | Fix bugs
v0.7 | First public version
