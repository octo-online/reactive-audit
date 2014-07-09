package com.octo.reactive.audit.lib;

/**
 * Exception throw by the JVM agent if a blocking API is used.
 * This exception is throw only if the auditReactive.throwExceptions parameter is true.
 *
 * @author Philippe PRADOS
 * @since 1.0
 */

public abstract class AuditReactiveException extends AssertionError
{
	/* Calculate the package name of the project. */
	private static final String  auditPackageName          =
			AuditReactiveException.class.getPackage().getName().replaceFirst("\\.[^.]+$", "");
	/* If debug, use a limited stack trace. */
	private static final int     LIMIT_STACK_SIZE_IF_DEBUG = 10;
	/* This variable was set by the javaagent, via introspection.
	   Then, it is not declared public.
	 */
	static  /*package*/  boolean debug                     = false;

	/* The threadname with the exception was create. */
	private String  threadName;
	/* The latency of this exception. */
	private Latency latency;

	/**
	 * Create an {@link AssertionError} with {@link Latency}, thread name and message.
	 *
	 * @param latency The latency for this exception.
	 * @param message The message associated with this exception.
	 */
	protected AuditReactiveException(Latency latency, String message)
	{
		super(message);
		threadName = Thread.currentThread().getName();
		this.latency = latency;
		updateStackTraceElements();
	}

	/**
	 * Create an {@link AssertionError} with {@link Latency}, thread name and formatted message.
	 *
	 * @param latency The latency for this exception.
	 * @param format The format message associated with this exception.
	 * @param args The arguments to generate the message with the format.
	 */
	protected AuditReactiveException(Latency latency, String format, Object... args)
	{
		super(String.format(format, args));
		threadName = Thread.currentThread().getName();
		updateStackTraceElements();
	}

	/**
	 * @return The latency associated with this exception.
	 */
	public Latency getLatency()
	{
		return latency;
	}

	/**
	 * @return a String with "&lt;message&gt; at thread &lt;thread name&gt;"
	 */
	@Override
	public String toString()
	{
		return super.toString() + " at thread " + threadName;
	}

	/**
	 * If not debug, remove all the audit layout in the stack trace.
	 */
	private void updateStackTraceElements()
	{
		if (!debug)
		{
			// Filter stack trace
			StackTraceElement[] stack = getStackTrace();
			int pos = 0;
			for (StackTraceElement traceElement : stack)
			{
				if (!traceElement.getClassName().startsWith(auditPackageName)
						|| traceElement.getClassName().endsWith("Test")) // For inner unit test
				{
					break;
				}
				++pos;
			}
			StackTraceElement[] newStack = new StackTraceElement[stack.length - pos];
			System.arraycopy(stack, pos, newStack, 0, newStack.length);
			setStackTrace(newStack);
		}
		else
		{
			StackTraceElement[] stack = getStackTrace();
			int newSize = Math.min(stack.length, LIMIT_STACK_SIZE_IF_DEBUG);
			StackTraceElement[] newStack = new StackTraceElement[newSize];
			System.arraycopy(stack, 0, newStack, 0, newSize);
			setStackTrace(newStack);
		}
	}

}
