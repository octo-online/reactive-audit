package com.octo.reactive.audit.lib;

/**
 * Exception throw by the jvm agent if a network blocking API is used.
 * This exception is throw only if the throwExceptions parameter is true.
 *
 * @author Philippe PRADOS
 */

public class NetworkAuditReactiveException extends AuditReactiveException
{
	public NetworkAuditReactiveException(String message)
	{
		super("Call method " + message);
	}

	public NetworkAuditReactiveException(String format, Object... args)
	{
		super("Call method " + format, args);
	}
}
