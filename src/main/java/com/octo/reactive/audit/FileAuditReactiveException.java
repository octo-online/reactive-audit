package com.octo.reactive.audit;

/**
 * Created by pprados on 06/05/14.
 */
public class FileAuditReactiveException extends AuditReactiveException
{
    public FileAuditReactiveException(String message)
    {
        super("File I/O:"+message);
    }
    public FileAuditReactiveException(String format, String... args)
    {
        super("File I/O:"+format,args);
    }
}
