package com.octo.reactive.audit.annotation;

/**
 * Exception throw by the jvm agent if a blocking API is used.
 * This exception is throw only if the throwExceptions parameter is true.
 * @author Philippe PRADOS
 */

public class AuditReactiveException extends AssertionError
{
    public AuditReactiveException(String message)
    {
        super(message);
    }
    public AuditReactiveException(String format, Object... args)
    {
        super(String.format(format,args));
    }
}
