package com.octo.reactive.audit.annotation;

/**
 * Exception throw by the jvm agent if a network blocking API is used.
 * This exception is throw only if the throwExceptions parameter is true.
 * @author Philippe PRADOS
 */

public class NetworkAuditReactiveException extends AuditReactiveException
{
    public NetworkAuditReactiveException(String message)
    {
        super("Network I/O:"+message);
    }
    public NetworkAuditReactiveException(String format, Object... args)
    {
        super("Network I/O:"+format,args);
    }
}
