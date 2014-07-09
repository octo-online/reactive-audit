package com.octo.reactive.audit.lib;

/**
 * Exception throw by the jvm agent if a network blocking API is used.
 * This exception is throw only if the throwExceptions parameter is true.
 *
 * @author Philippe PRADOS
 * @since 1.0
 */

public class NetworkAuditReactiveException extends AuditReactiveException
{
	/**
	 * Create an {@link AssertionError} with {@link Latency}, thread name and message.
	 *
	 * @param latency The latency for this exception.
	 * @param message The message associated with this exception.
	 */
	public NetworkAuditReactiveException(Latency latency, String message)
	{
		super(latency, "Call method " + message);
	}

	/**
	 * Create an {@link AssertionError} with {@link Latency}, thread name and formatted message.
	 *
	 * @param latency The latency for this exception.
	 * @param format The format message associated with this exception.
	 * @param args The arguments to generate the message with the format.
	 */
	public NetworkAuditReactiveException(Latency latency, String format, Object... args)
	{
		super(latency, "Call method " + format, args);
	}
}
