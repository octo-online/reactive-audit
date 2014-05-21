package com.octo.reactive.audit;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Stack;
import java.util.regex.Pattern;

import static com.octo.reactive.audit.Logger.Level;
import static com.octo.reactive.audit.Logger.Level.Error;
import static com.octo.reactive.audit.Logger.Level.*;

/**
 * Created by pprados on 07/05/14.
 */
public class ConfigAuditReactive
{
	/**
	 * The singleton with the current parameters.
	 */
	public static final  ConfigAuditReactive config                 = new ConfigAuditReactive();
	/**
	 * A transaction with 'strict' parameters.
	 */
	public static final  Transaction         strict                 =
			config.begin()
					.throwExceptions(true)
					.log(None)
					.threadPattern(".*")
					.bootStrapDelay(0)
					.seal();
	/**
	 * A transaction with 'log only' parameters.
	 */
	public static final  Transaction         logOnly                =
			config.begin()
					.throwExceptions(false)
					.log(Warn)
							//.threadPattern(DEFAULT_THREAD_PATTERN)
					.bootStrapDelay(0)
					.seal();
	/**
	 * A transaction with 'off' audit.
	 */
	public static final  Transaction         off                    =
			config.begin()
					.throwExceptions(false)
					.log(Error)
					.threadPattern("(?!)")
					.bootStrapDelay(0)
					.seal();
	// It's easy to rename the package
	// FIXME: testu
	static final         String              myPackage              = ConfigAuditReactive.class.getPackage().getName();
	private static final String              DEFAULT_THREAD_PATTERN = "^(ForkJoinPool-.*)";
	private              Pattern             threadPattern          = Pattern.compile(DEFAULT_THREAD_PATTERN);
	Logger logger = new Logger();
	private          boolean              throwExceptions = false;
	private          long                 bootstrapStart  = 0L;
	private          long                 bootstrapDelay  = 0L;
	private          boolean              afterBootstrap  = false;
	private volatile boolean              started         = false;
	private          ThreadLocal<Integer> suppressAudit   = new ThreadLocal()
	{
		@Override
		protected Integer initialValue()
		{
			return 0;
		}
	};
	private          Stack<Transaction>   stack           = new Stack<Transaction>();
	private          HistoryStackElement  history         = new HistoryStackElement(this);

	synchronized void startup()
	{
		if (!started)
		{
			bootstrapStart = System.currentTimeMillis();
			Properties properties = new Properties();
			String url = System.getenv("auditReactive");  // FIXME: costrante
			if (url == null) url = LoadParams.DEFAULT_FILENAME;
			url = System.getProperty("auditReactive", url);
			new LoadParams(this, url).commit();
			started = true;
			logger.info("Start");
		}
	}

	void shutdown()
	{
		if (started)
		{
			logger.info("Shutdown");
			started = false;
		}
	}

	/**
	 * Increment the thread local variable to suppress audit for the current frame.
	 */
	void incSuppress()
	{
		suppressAudit.set(suppressAudit.get() + 1);
	}

	/**
	 * Decrement the thread local variable to suppress audit for the current frame.
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
	 * Ask if supress the audit for the current frame.
	 *
	 * @return <code>true</code> if refuse the audit now.
	 */
	boolean isSuppressAudit()
	{
		return suppressAudit.get() != 0;
	}

	boolean isAfterStartupDelay()
	{
		if (afterBootstrap) return true;
		if ((System.currentTimeMillis() - bootstrapStart) > bootstrapDelay)
		{
			afterBootstrap = true;
			return true;
		}
		return false;
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
	 * Return the current thread pattern.
	 *
	 * @return The current thread pattern.
	 */
	public String getThreadPattern()
	{
		return threadPattern.toString();
	}

	public Level getLogLevel()
	{
		return logger.getLogLevel();
	}

	/**
	 * @return Return a transaction with the current value.
	 */
	private Transaction current()
	{
		return new Transaction()
				.log(logger.getLogLevel())
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
	 * Begin a transaction to update the parameters.
	 * Call commit() to apply.
	 * @return The transaction.
	 */
	public Transaction begin()
	{
		return new Transaction();
	}

	public void logIfNew(Level level, Object msg)
	{
		if (!history.isAlreadyLogged())
		{
			logger.log(level, msg);
		}
	}

	/**
	 * A current transcation to modify the parameters.
	 */
	public class Transaction
	{
		private List<Runnable> cmds = new ArrayList<>();
		private boolean sealed;

		private void add(Runnable cmd) throws IllegalArgumentException
		{
			if (sealed) throw new IllegalArgumentException("Sealed");
			cmds.add(cmd);
		}

		/**
		 * Seal the transaction.
		 *
		 * @return this
		 */
		/*package*/ Transaction seal()
		{
			sealed = true;
			return this;
		}

		/**
		 * Ask to throw an exception if detect an error.
		 * May be apply after the commit().
		 *
		 * @param onOff true or fall
		 * @return The current transcation.
		 */
		public Transaction throwExceptions(boolean onOff)
		{
			add(() -> throwExceptions = onOff);
			return this;
		}

		/**
		 * Select the trace logLevel.
		 * May be apply after the commit().
		 *
		 * @param level The logLevel.
		 * @return The current transcation.
		 */
		public Transaction log(Level level)
		{
			add(() -> logger.setLogLevel(level));
			return this;
		}

		/**
		 * Ask a specific pattern for detect the reactive thread.
		 * May be apply after the commit().
		 *
		 * @param pattern The regexp pattern.
		 * @return The current transaction.
		 */
		public Transaction threadPattern(String pattern)
		{
			add(() -> threadPattern = Pattern.compile(pattern));
			return this;
		}

		/**
		 * Ask a specific boot strap delay before start the audit.
		 * May be apply after the commit().
		 *
		 * @param delay The new delay.
		 * @return The current transaction.
		 */
		public Transaction bootStrapDelay(long delay)
		{
			add(() -> bootstrapDelay = delay);
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
}
