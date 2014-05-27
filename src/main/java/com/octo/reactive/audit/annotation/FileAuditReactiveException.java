package com.octo.reactive.audit.annotation;

/**
 * Exception throw by the jvm agent if a file blocking API is used.
 * This exception is throw only if the throwExceptions parameter is true.
 * @author Philippe PRADOS
 */

public class FileAuditReactiveException extends AuditReactiveException
{
    public FileAuditReactiveException(String message)
    {
        super("File I/O:"+message);
    }
    public FileAuditReactiveException(String format, Object... args)
    {
        super("File I/O:"+format,args);
    }
}
