package com.octo.reactive.audit.lib;

/**
 * Exception throw by the jvm agent if a blocking API is used.
 * This exception is throw only if the throwExceptions parameter is true.
 *
 * @author Philippe PRADOS
 */

public class AuditReactiveException extends AssertionError
{
	private static final String auditPackageName =
			AuditReactiveException.class.getPackage().getName().replaceFirst("\\.[^.]+$", "");
	private String threadName;

	public AuditReactiveException(String message)
	{
		super(message);
		threadName = Thread.currentThread().getName();
		updateStackTraceElements();
	}

	public AuditReactiveException(String format, Object... args)
	{
		super(String.format(format, args));
		threadName = Thread.currentThread().getName();
		updateStackTraceElements();
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
