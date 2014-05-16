package com.octo.reactive.audit;

/**
 * Created by pprados on 06/05/14.
 */
// FIXME: dans le package client

public class NetworkAuditReactiveException extends AuditReactiveException
{
    public NetworkAuditReactiveException(String message)
    {
        super("Network I/O:"+message);
    }
    public NetworkAuditReactiveException(String format, String... args)
    {
        super("Network I/O:"+format,args);
    }
}
