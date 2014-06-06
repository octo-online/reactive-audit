package com.octo.reactive.audit.lib;

/**
 * Exception throw by the jvm agent if a file blocking API is used.
 * This exception is throw only if the throwExceptions parameter is true.
 *
 * @author Philippe PRADOS
 */

public class FileAuditReactiveException extends AuditReactiveException
{
	public FileAuditReactiveException(String message)
	{
		super("Call method " + message);
	}

	public FileAuditReactiveException(String format, Object... args)
	{
		super("Call method " + format, args);
	}
}
