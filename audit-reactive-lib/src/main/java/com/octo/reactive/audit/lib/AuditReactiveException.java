package com.octo.reactive.audit.lib;

/**
 * Exception throw by the jvm agent if a blocking API is used.
 * This exception is throw only if the throwExceptions parameter is true.
 *
 * @author Philippe PRADOS
 */

public abstract class AuditReactiveException extends AssertionError
{
	private static final String  auditPackageName =
			AuditReactiveException.class.getPackage().getName().replaceFirst("\\.[^.]+$", "");
	static               boolean debug            = false;
	private String  threadName;
	private Latency latency;

	public AuditReactiveException(Latency latency, String message)
	{
		super(message);
		threadName = Thread.currentThread().getName();
		this.latency = latency;
		updateStackTraceElements();
	}

	public AuditReactiveException(Latency latency, String format, Object... args)
	{
		super(String.format(format, args));
		threadName = Thread.currentThread().getName();
		updateStackTraceElements();
	}

	public Latency getLatency()
	{
		return latency;
	}

	@Override
	public String toString()
	{
		return super.toString() + " at thread " + threadName;
	}

	/**
	 * Remove all the audit layout in the stack trace.
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
	}

}
