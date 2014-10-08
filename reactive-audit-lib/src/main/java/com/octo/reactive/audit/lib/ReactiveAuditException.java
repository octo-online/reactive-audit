/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit.lib;

/**
 * Exception thrown by the JVM agent if a blocking API is used.
 * This exception is thrown only if the reactiveAudit_throwExceptions parameter is set to <b>true</b>.
 *
 * @author Philippe PRADOS
 * @since 1.0
 */
public class ReactiveAuditException extends AssertionError
{
	/* This variable was set by the javaagent, via introspection.
	   Thus it is not declared public.
	 */
	@SuppressWarnings("StaticNonFinalField")
	/*!final !!!*/static boolean debug             = false;
	/* Calculate the package name of the project. */
	private static final      String  auditPackageName          =
			ReactiveAuditException.class.getPackage().getName().replaceFirst("\\.[^.]+$", "");
	/* If debug, use a limited stack trace. */
	private static final      int     LIMIT_STACK_SIZE_IF_DEBUG = 10;
	/* The threadName with the exception is created. */
	private final String  threadName;
	/* The latency of this exception. */
	private       Latency latency;

	/**
	 * Create an {@link AssertionError} with {@link Latency}, thread name and message.
	 *
	 * @param latency The latency for this exception.
	 * @param message The message associated with this exception.
	 */
	protected ReactiveAuditException(Latency latency, String message)
	{
		super(message);
		this.threadName = Thread.currentThread().getName();
		this.latency = latency;
		updateStackTraceElements();
	}

	/**
	 * Create an {@link AssertionError} with {@link Latency}, thread name and formatted message.
	 *
	 * @param latency The latency for this exception.
	 * @param format  The format message associated with this exception.
	 * @param args    The arguments to generate the message with the format.
	 */
	protected ReactiveAuditException(Latency latency, String format, Object... args)
	{
		super(String.format(format, args));
		this.threadName = Thread.currentThread().getName();
		updateStackTraceElements();
	}

	/**
	 * @return The latency associated with this exception.
	 */
	public Latency getLatency()
	{
		return this.latency;
	}

	/**
	 * @return a String with "&lt;message&gt; at thread &lt;thread name&gt;"
	 */
	@Override
	public String toString()
	{
		return super.toString() +
				System.getProperty("line.separator")+
				"\tat thread \"" + this.threadName + '"';
	}

	/**
	 * If not debug, remove all the audit layout in the stack trace.
	 */
	@SuppressWarnings("PointlessBooleanExpression")
	private void updateStackTraceElements()
	{
		if (!debug) // Warning: the debug value can be updated via introspection.
		{
			// Filter stack trace
			final StackTraceElement[] stack = getStackTrace();
			int pos = 0;
			for (final StackTraceElement traceElement : stack)
			{
				if (!traceElement.getClassName().startsWith(auditPackageName)
						|| traceElement.getClassName().endsWith("Test")) // For inner unit test
				{
					break;
				}
				++pos;
			}
			final StackTraceElement[] newStack = new StackTraceElement[stack.length - pos];
			System.arraycopy(stack, pos, newStack, 0, newStack.length);
			setStackTrace(newStack);
		}
		else
		{
			final StackTraceElement[] stack = getStackTrace();
			final int newSize = Math.min(stack.length, LIMIT_STACK_SIZE_IF_DEBUG);
			final StackTraceElement[] newStack = new StackTraceElement[newSize];
			System.arraycopy(stack, 0, newStack, 0, newSize);
			setStackTrace(newStack);
		}
	}

}
