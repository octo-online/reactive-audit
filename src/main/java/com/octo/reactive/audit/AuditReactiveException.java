package com.octo.reactive.audit;

/**
 * Created by pprados on 06/05/14.
 */
// FIXME: dans le package client

public class AuditReactiveException extends AssertionError
{
    public AuditReactiveException(String message)
    {
        super(message);
    }
    public AuditReactiveException(String format, String... args)
    {
        super(String.format(format,args));
    }
}
