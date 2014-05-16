package com.octo.reactive.audit;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by pprados on 07/05/14.
 */
// FIXME: dans le package client

// TODO : systemProperty 'some.prop', 'value'
// TODO : java.io.File
    // TODO: testu de cette classe
public class ConfigAuditReactive
{
	public static final int None     = 3;// FIXME: 0;
	public static final int Error    = 1;
	public static final int Warn     = 2;
	public static final int Info     = 3;
	private             int logLevel = None;

	void setLogLevel(int aLevel)
	{
		logLevel = aLevel;
	}

	public void error(Object msg)
	{
		if (logLevel >= Error)
			log_(Error, msg.toString());
	}

	public void error(String format, String... args)
	{
		if (logLevel >= Error)
			log_(Error, String.format(format, args));
	}

	public void warn(Object msg)
	{
		if (logLevel >= Warn)
			log_(Warn, msg.toString());
	}

	public void warn(String format, String... args)
	{
		if (logLevel >= Warn)
			log_(Warn, String.format(format, args));
	}

	public void info(Object msg)
	{
		if (logLevel >= Info)
			log_(Info, msg.toString());
	}

	public void info(String format, String... args)
	{
		if (logLevel >= Info)
			log_(Info, String.format(format, args));
	}

	final String[] levels = new String[]
			{
					"",
					"Error:",
					"Warn:",
					"Info:"
			};

	private void log_(int level, String msg)
	{
		System.err.println(levels[level] + msg); // FIXME
	}



	private static final String defaultThreadPattern="^(ForkJoinPool-.*|Test worker)";
	private       boolean throwExceptions = false;
	private       Pattern threadPattern   = Pattern.compile(defaultThreadPattern);
	private       long bootstrapDelay = 0L;
	private ThreadLocal<Integer> suppressAudit=new ThreadLocal()
	{
		@Override protected Integer initialValue() {
			return 0;
		}
	};
	private Stack<Transaction> stack = new Stack<>();

	/**
	 * Increment the thread local variable to suppress audit for the current stack.
	 */
	void incSuppress()
	{
		suppressAudit.set(suppressAudit.get() + 1);
	}

	/**
	 * Decrement the thread local variable to suppress audit for the current stack.
	 */
	void decSuppress()
	{
		suppressAudit.set(suppressAudit.get() - 1);
	}

	/**
	 * Return the current suppress counter. For unit test only.
	 * @return The local variable.
	 */
	int getSuppress()
	{
		return suppressAudit.get();
	}

	/**
	 * Check if the current thread name match the pattern.
	 * @param name The thread name.
	 * @return <code>true</code> if match.
	 */
	boolean isThreadNameMatch(String name)
	{
		return threadPattern.matcher(name).matches();
	}

	/**
	 * Ask if supress the audit for the current stack.
	 *
	 * @return <code>true</code> if refuse the audit now.
	 */
	boolean isSuppressAudit()
	{
		return suppressAudit.get()!=0;
	}
	/**
	 * Return the current bootstrap delay before start the audit.
	 * @return The delay in MS after the startup.
	 */
	public long getBootstrapDelay()
	{
		return bootstrapDelay;
	}

	/**
	 * Throw an exception if detect an error ?
	 * @return the current status.
	 */
	public boolean isThrow()
	{
		//logger.warn("Throw exception");
		return throwExceptions;
	}

	/**
	 * Return the current log logLevel.
	 * @return The log logLevel.
	 */
	public int getLogLevel()
	{
		return logLevel;
	}
	/**
	 * Return the current thread pattern.
	 * @return The current thread pattern.
	 */
	public String getThreadPattern()
	{
		return threadPattern.toString();
	}

	/**
	 * @return Return a transaction with the current value.
	 */
	private Transaction current()
	{
		return new Transaction()
				.log(logLevel)
				.throwExceptions(throwExceptions)
				.threadPattern(threadPattern.toString())
				.bootStrapDelay(bootstrapDelay);
	}

	/**
	 * Push the current values.
	 */
	protected void push()
	{
		stack.push(current());
	}

	/**
	 * Apply the last values.
	 */
	protected void pop()
	{
		stack.pop().commit();
	}

	/**
	 * A current transcation to modify the parameters.
	 */
	public class Transaction
	{
		private List<Runnable> cmds = new ArrayList<>();

		/**
		 * Ask to throw an exception if detect an error.
		 * May be apply after the commit().
		 * @param onOff true or fall
		 * @return The current transcation.
		 */
		public Transaction throwExceptions(boolean onOff)
		{
			cmds.add(() -> throwExceptions = onOff);
			return this;
		}

		/**
		 * Select the trace logLevel.
		 * May be apply after the commit().
		 * @param level The logLevel.
		 * @return The current transcation.
		 */
		public Transaction log(int level)
		{
			cmds.add(() -> setLogLevel(level));
			return this;
		}

		/**
		 * Ask a specific pattern for detect the reactive thread.
		 * May be apply after the commit().
		 * @param pattern The regexp pattern.
		 * @return The current transaction.
		 */
		public Transaction threadPattern(String pattern)
		{
			cmds.add(() -> threadPattern = Pattern.compile(pattern));
			return this;
		}

		/**
		 * Ask a specific boot strap delay before start the audit.
		 * May be apply after the commit().
		 * @param delay The new delay.
		 * @return The current transaction.
		 */
		public Transaction bootStrapDelay(long delay)
		{
			cmds.add(()-> bootstrapDelay=delay);
			return this;
		}

		/**
		 * Apply all the modifications describe in the transcation.
		 * Can be apply many times.
		 */
		public synchronized void commit()
		{
			cmds.forEach(x -> x.run());
		}
	}

	/**
	 * Begin a transaction to update the parameters.
	 * Call commit() to apply.
	 * @return The transaction.
	 */
	public Transaction begin()
	{
		return new Transaction();
	}

	/**
	 * The singleton with the current parameters.
	 */
	public static final ConfigAuditReactive config  = new ConfigAuditReactive();
	/**
	 * A transaction with 'strict' parameters.
	 */
	public static final Transaction         strict  =
			config.begin()
					.throwExceptions(true)
					.log(None)
					.threadPattern(".*")
					.bootStrapDelay(0);
	/**
	 * A transaction with 'log only' parameters.
	 */
	public static final Transaction         logOnly =
			config.begin()
					.throwExceptions(false)
					.log(Warn)
					.threadPattern(defaultThreadPattern)
					.bootStrapDelay(0);
	/**
	 * A transaction with 'off' audit.
	 */
	public static final Transaction         off     =
			config.begin()
					.throwExceptions(false)
					.log(Error)
					.threadPattern("(?!)")
					.bootStrapDelay(0);
}
