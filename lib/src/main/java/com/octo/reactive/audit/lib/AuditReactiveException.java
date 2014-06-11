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

	public AuditReactiveException(String message)
	{
		super(message);
		updateStackTraceElements();
	}

	public AuditReactiveException(String format, Object... args)
	{
		super(String.format(format, args));
		updateStackTraceElements();
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
