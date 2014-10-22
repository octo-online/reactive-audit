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

package com.octo.reactive.audit;

import static com.octo.reactive.audit.LoadParams.DEFAULT_FILENAME;
import static com.octo.reactive.audit.LoadParams.DEFAULT_LOG_FORMAT;
import static com.octo.reactive.audit.LoadParams.DEFAULT_LOG_LEVEL;
import static com.octo.reactive.audit.LoadParams.DEFAULT_LOG_OUTPUT;
import static com.octo.reactive.audit.LoadParams.DEFAULT_LOG_SIZE;
import static com.octo.reactive.audit.LoadParams.DEFAULT_THREAD_PATTERN;
import static com.octo.reactive.audit.LoadParams.KEY_AUDIT_FILENAME;
import static com.octo.reactive.audit.LoadParams.KEY_BOOTSTRAP_DELAY;
import static com.octo.reactive.audit.LoadParams.KEY_CPU_LATENCY;
import static com.octo.reactive.audit.LoadParams.KEY_FILE_LATENCY;
import static com.octo.reactive.audit.LoadParams.KEY_NETWORK_LATENCY;
import static com.octo.reactive.audit.LoadParams.KEY_THREAD_PATTERN;
import static com.octo.reactive.audit.LoadParams.KEY_THROW_EXCEPTIONS;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.octo.reactive.audit.lib.CPUReactiveAuditException;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import com.octo.reactive.audit.lib.ReactiveAuditException;

@SuppressWarnings("RefusedBequest")
public class ReactiveAudit
{
	/**
	 * The singleton with the current parameters.
	 */
	public static final ReactiveAudit        config            = new ReactiveAudit();
	/**
	 * A transaction with 'strict' parameters.
	 */
	public static final Transaction          strict            =
			config.begin()
					.throwExceptions(true)
					.threadPattern(".*")
					.bootStrapDelay(0)
					.seal();
	/**
	 * A transaction with 'log only' parameters.
	 */
	public static final Transaction          logOnly           =
			config.begin()
					.throwExceptions(false)
					.logOutput(DEFAULT_LOG_OUTPUT, DEFAULT_LOG_FORMAT, DEFAULT_LOG_SIZE)
					.threadPattern(DEFAULT_THREAD_PATTERN)
					.bootStrapDelay(0)
					.seal();
	/**
	 * A transaction with 'off' audit.
	 */
	public static final Transaction          off               =
			config.begin()
					.throwExceptions(false)
					.threadPattern("(?!)")
					.bootStrapDelay(0)
					.seal();
	// Help to rename the package
	public static final String               auditPackageName  = ReactiveAudit.class.getPackage().getName();
	public final        Logger               logger            = Logger.getAnonymousLogger();
	// FIXME : Try to use a fake logger to test Jboss. Jboss has a problem with Aspectj :-(
//	public final        FakeLogger          logger             = new FakeLogger();
	private final       LongAdder            statLow           = new LongAdder();
	private final       LongAdder            statMedium        = new LongAdder();
	private final       LongAdder            statHigh          = new LongAdder();
	private final       AtomicInteger        statMaxThread     = new AtomicInteger(0);
	private final       ThreadLocal<Integer> suppressAudit     = new ThreadLocal<Integer>()
	{
		@Override
		protected Integer initialValue()
		{
			return 0;
		}
	};
	private final       Stack<Transaction>   stack             = new Stack<Transaction>();
	private final       HistoryStackElement  history           = new HistoryStackElement();
	private final       Set<String>          historyThreadName = Collections.synchronizedSet(new TreeSet<String>());
	/*package*/ volatile boolean started = false;
	private Pattern threadPattern   = Pattern.compile(DEFAULT_THREAD_PATTERN);
	private boolean throwExceptions = false;
	private long    bootstrapStart  = 0L;
	private long    bootstrapDelay  = 0L;
	private boolean afterBootstrap  = false;
	private Latency latencyFile     = Latency.MEDIUM;
	private Latency latencyNetwork  = Latency.LOW;
	private Latency latencyCPU      = Latency.MEDIUM;
	private boolean debug           = false;
	private Handler logHandler;

    public static String getPropertiesURL()
	{
		String url = System.getenv(KEY_AUDIT_FILENAME);
		if (url == null) url = DEFAULT_FILENAME;
		url = System.getProperty(KEY_AUDIT_FILENAME, url);
		return url.trim();
	}

	synchronized void startup()
	{
		if (!this.started)
		{
			this.started = true;
			this.bootstrapStart = System.currentTimeMillis();
			logOnly.commit();
			final String url = getPropertiesURL();
			new LoadParams(this, url).commit();
			this.logger.config("Start reactive audit with " + FileTools.homeFile(url));
            if (config.logger.isLoggable(Level.FINE))
            {
                config.logger.fine(String.format("%-30s = %s",KEY_THREAD_PATTERN,config.getThreadPattern()));
                config.logger.fine(String.format("%-30s = %s",KEY_THROW_EXCEPTIONS,config.isThrow()));
                config.logger.fine(String.format("%-30s = %s",KEY_BOOTSTRAP_DELAY,config.getBootstrapDelay()));
                config.logger.fine(String.format("%-30s = %s",KEY_FILE_LATENCY, config.getFileLatency()));
                config.logger.fine(String.format("%-30s = %s",KEY_NETWORK_LATENCY,config.getNetworkLatency()));
                config.logger.fine(String.format("%-30s = %s",KEY_CPU_LATENCY ,config.getCPULatency()));
            }
		}
	}


    synchronized void shutdown()
	{
        // Just shortly
        synchronized (LogManager.getLogManager())
        {
            final String cr = System.getProperty("line.separator");
            final long low = this.statLow.sum();
            final long medium = this.statMedium.sum();
            final long high = this.statHigh.sum();
            final StringBuilder buf =
                    new StringBuilder(cr)
                            .append("\tTotal high  =").append(high).append(cr)
                            .append("\tTotal medium=").append(medium).append(cr)
                            .append("\tTotal low   =").append(low).append(cr)
                            .append("\tMax. concurrent threads=").append(this.statMaxThread.get())
                            .append(" (Number of node:").append(Runtime.getRuntime().availableProcessors()).append(")").append(cr)
                            .append("Shutdown audit");
            this.logger.config(buf.toString());
            if (this.logHandler != null) {
                this.logHandler.close();
            }
        }
	}

	void reset()
	{
		this.started = false;
		this.afterBootstrap = false;
		this.history.purge();
		this.stack.clear();
		this.historyThreadName.clear();
		startup();
	}

	public boolean isDebug()
	{
		return this.debug;
	}

	void setDebug(boolean debug)
	{
		this.debug = debug;
		this.logger.setLevel((debug) ? Level.FINE : DEFAULT_LOG_LEVEL);
		try
		{
			final Field field = ReactiveAuditException.class.getDeclaredField("debug");
			field.setAccessible(true);
			field.set(null, debug);
		}
		catch (final Exception e)
		{
			// Ignore
			this.logger.config("You detect a bug !"+System.getenv("line.separator")+ e.getMessage());
		}
	}

	/**
	 * Increment the thread local variable to suppress audit for the current frame.
	 */
	public void incSuppress()
	{
		this.suppressAudit.set(this.suppressAudit.get() + 1);
	}

	/**
	 * Decrement the thread local variable to suppress audit for the current frame.
	 */
	public void decSuppress()
	{
		this.suppressAudit.set(this.suppressAudit.get() - 1);
	}

	/**
	 * Return the current suppress counter. For unit test only.
	 *
	 * @return The local variable.
	 */
	int getSuppress()
	{
		return this.suppressAudit.get();
	}

	/**
	 * Check if the current thread name match the pattern.
	 *
	 * @param name The thread name.
	 * @return <code>true</code> if match.
	 */
	boolean isThreadNameMatch(String name)
	{
		if (name == null) return false;
		if (this.threadPattern == null)
		{
			return false;
		}
		if (this.historyThreadName.add(name))
		{
			final int now = ManagementFactory.getThreadMXBean().getThreadCount();
			int old;
			for (; ; )
			{
				old = this.statMaxThread.get();
				if (now > old)
				{
					if (this.statMaxThread.compareAndSet(old, now))
						break;
				}
				else
					break;
				Thread.yield();
			}
			if (this.debug) this.logger.fine("Detect thread name \"" + name + "\"");
		}
		return this.threadPattern.matcher(name).matches();
	}

	/**
	 * Ask if supress the audit for the current frame.
	 *
	 * @return <code>true</code> if refuse the audit now.
	 */
	boolean isSuppressAudit()
	{
		return this.suppressAudit.get() != 0;
	}

	boolean isAfterStartupDelay()
	{
		if (this.afterBootstrap) return true;
		if ((System.currentTimeMillis() - this.bootstrapStart) > (this.bootstrapDelay*1000))
		{
			this.afterBootstrap = true;
			return true;
		}
		return false;
	}

	/**
	 * Return the current bootstrap delay before start the audit.
	 *
	 * @return The delay in MS after the startup.
	 */
	public long getBootstrapDelay()
	{
		return this.bootstrapDelay;
	}

	public Latency getFileLatency()
	{
		return this.latencyFile;
	}

	public Latency getNetworkLatency()
	{
		return this.latencyNetwork;
	}

	public Latency getCPULatency()
	{
		return this.latencyCPU;
	}

	/**
	 * Throw an exception if detect an error ?
	 *
	 * @return the current status.
	 */
	public boolean isThrow()
	{
		//logger.warn("Throw exception");
		return this.throwExceptions;
	}

	/**
	 * Return the current thread pattern.
	 *
	 * @return The current thread pattern.
	 */
	public String getThreadPattern()
	{
		return this.threadPattern.toString();
	}

	/**
	 * @return Return a transaction with the current value.
	 */
	private Transaction current()
	{
		return new Transaction()
				.throwExceptions(this.throwExceptions)
				.threadPattern(this.threadPattern.toString())
				.bootStrapDelay(this.bootstrapDelay);
	}

	/**
	 * Push the current values.
	 */
	protected void push()
	{
		this.stack.push(current());
	}

	/**
	 * Apply the last values.
	 */
	protected void pop()
	{
		this.stack.pop().commit();
	}

	/**
	 * Begin a transaction to update the parameters.
	 * Call commit() to apply.
	 *
	 * @return The transaction.
	 */
	public Transaction begin()
	{
		return new Transaction();
	}

	@SuppressWarnings({"ChainOfInstanceofChecks", "InstanceofInterfaces"})
	public void logIfNew(Latency latencyLevel, ReactiveAuditException e)
	{
		Latency baseLatency = null;
		if (e instanceof FileReactiveAuditException)
			baseLatency = this.latencyFile;
		else if (e instanceof NetworkReactiveAuditException)
			baseLatency = this.latencyNetwork;
		else if (e instanceof CPUReactiveAuditException)
			baseLatency = this.latencyCPU;
		if (baseLatency == null) return;
		if (e.getLatency().ordinal() < baseLatency.ordinal())
			return;

		if (!this.history.isAlreadyLogged(e.getStackTrace()))
		{
			Level level;
			switch (latencyLevel)
			{
				case LOW:
					level = Level.INFO;
					this.statLow.add(1);
					break;
				case MEDIUM:
					level = Level.WARNING;
					this.statMedium.add(1);
					break;
				default:
					level = Level.SEVERE;
					this.statHigh.add(1);
					break;
			}

			this.logger.log(level, e.getMessage(), e);
		}
	}

	public void debug(Object s)
	{
		this.logger.fine(String.valueOf(s));
	}

	public void dumpTarget(Object obj)
	{
		debug("DUMP TARGET: " + obj);
		final Class<?> cl = obj.getClass();
		debug(cl.getName() + " extends " + cl.getSuperclass());
		for (final Class<?> c : cl.getInterfaces())
		{
			ReactiveAudit.config.debug("implements " + c);
		}

	}

    /*
	public class FakeLogger //extends Logger
	{
		public Handler[] getHandlers()
		{
			return new Handler[0];
		}

		public void addHandler(Handler handler) throws SecurityException
		{
			//super.addHandler(handler);
		}

		public void removeHandler(Handler handler) throws SecurityException
		{
			//super.removeHandler(handler);
		}

		public void setUseParentHandlers(boolean b)
		{
		}

		public Logger getParent()
		{
			return null;
		}

		public void log(Level level, String msg)
		{

		}

		public void info(String msg)
		{
			System.err.println("INFO " + msg);
		}

		public void config(String msg)
		{
			System.err.println("CONFIG " + msg);

		}

		public void fine(String msg)
		{
			System.err.println("FINE " + msg);
		}

		public void finest(String msg)
		{
			System.err.println("FINEST " + msg);
		}

		public void severe(String msg)
		{
			System.err.println("SEVERE " + msg);
		}

		public void warning(String msg)
		{
			System.err.println("WARNING " + msg);
		}

		public void log(Level level, String msg, Object param1)
		{
			fine(msg);
		}

		public Level getLevel()
		{
			return Level.FINE;
		}

		public void setLevel(Level level)
		{
		}
//		protected EmptyLogger(String name, String resourceBundleName)
//		{
//			super(name, resourceBundleName);
//		}
	}
	*/

	/**
	 * A current transaction to modify the parameters.
	 */
	public class Transaction
	{
		private final List<Runnable> commands = new ArrayList<Runnable>();
		private boolean sealed;

		private void add(Runnable cmd)
				throws IllegalArgumentException
		{
			if (this.sealed) throw new IllegalArgumentException("Sealed");
			this.commands.add(cmd);
		}

		/**
		 * Seal the transaction.
		 *
		 * @return this
		 */
		/*package*/ Transaction seal()
		{
			this.sealed = true;
			return this;
		}

		/**
		 * Ask to throw an exception if detect an error.
		 * May be apply after the commit().
		 *
		 * @param onOff true or fall
		 * @return The current transaction.
		 */
		public Transaction throwExceptions(final boolean onOff)
		{
			add(new Runnable()
			{
				@Override
				public void run()
				{
					ReactiveAudit.this.throwExceptions = onOff;
				}
			});
			return this;
		}

		public Transaction latencyFile(String level)
		{
			final Latency latency = (level.length()) == 0 ? null : Latency.valueOf(level.toUpperCase());
			add(new Runnable()
			{
				@Override
				public void run()
				{
					ReactiveAudit.this.latencyFile = latency;
				}
			});
			return this;
		}

		public Transaction latencyNetwork(String level)
		{
			final Latency latency = (level.length()) == 0 ? null : Latency.valueOf(level.toUpperCase());
			add(new Runnable()
			{
				@Override
				public void run()
				{
					ReactiveAudit.this.latencyNetwork = latency;
				}
			});
			return this;
		}

		public Transaction latencyCPU(String level)
		{
			final Latency latency = (level.length()) == 0 ? null : Latency.valueOf(level.toUpperCase());
			add(new Runnable()
			{
				@Override
				public void run()
				{
					ReactiveAudit.this.latencyCPU = latency;
				}
			});
			return this;
		}

		/**
		 * Select the log output.
		 * May be apply after the commit().
		 *
		 * @param output "console" or file name.
		 * @param format The string format.
		 * @param size   The size before rolling the files.
		 * @return The current transaction.
		 */
		public Transaction logOutput(final String output, final String format, final String size)
		{
			try
			{
				final int isize = Integer.parseInt(size);
                final Handler handler = ("console".equalsIgnoreCase(output))
                        ? new ConsoleHandler()
                        : new FileHandler(output, isize,
                        (isize == 0) ? 1 : 5, false);
                if (output.endsWith(".xml"))
                    handler.setFormatter(new java.util.logging.XMLFormatter());
                else
                {
                    handler.setFormatter(new AuditLogFormat(format));
                }
				handler.setLevel(DEFAULT_LOG_LEVEL);
				add(new Runnable()
				{
					@Override
					public void run()
					{
						for (final Handler h : ReactiveAudit.this.logger.getHandlers())
						{
							ReactiveAudit.this.logger.removeHandler(h);
						}
						ReactiveAudit.this.logHandler = handler;
						ReactiveAudit.this.logger.addHandler(handler);
						ReactiveAudit.this.logger.setUseParentHandlers(false);
						final Level level=(ReactiveAudit.this.debug) ? Level.FINE : DEFAULT_LOG_LEVEL;
						ReactiveAudit.this.logger.setLevel(level);
						handler.setLevel(level);
					}
				});
			}
			catch (final IOException e)
			{
				ReactiveAudit.this.logger.severe("Log file error (" + e.getMessage() + ")");
			} catch (final Throwable th) {
			    System.err.println("ERROR: " + th.getMessage());
			    th.printStackTrace();
			}
			return this;
		}

		/**
		 * Ask a specific pattern for detect the reactive thread.
		 * May be apply after the commit().
		 *
		 * @param pattern The regexp pattern.
		 * @return The current transaction.
		 */
		public Transaction threadPattern(final String pattern)
		{
			add(new Runnable()
			{
				@Override
				public void run()
				{
					ReactiveAudit.this.threadPattern = Pattern.compile(pattern);
				}
			});
			return this;
		}

		/**
		 * Ask a specific boot strap delay before start the audit.
		 * May be apply after the commit().
		 *
		 * @param delay The new delay.
		 * @return The current transaction.
		 */
		public Transaction bootStrapDelay(final long delay)
		{
			add(new Runnable()
			{
				@Override
				public void run()
				{
					ReactiveAudit.this.bootstrapDelay = delay;
				}
			});
			return this;
		}

		/**
		 * Set the debug mode.
		 * With debug mode, the exception was not clean with the aspect.
		 * May be apply after the commit().
		 *
		 * @param aDebug The debug mode.
		 * @return The current transaction.
		 */
		public Transaction debug(final boolean aDebug)
		{
			add(new Runnable()
			{
				@Override
				public void run()
				{
					setDebug(aDebug);
				}
			});
			return this;
		}

		/**
		 * Apply all the modifications describe in the transcation.
		 * Can be apply many times.
		 */
		public synchronized void commit()
		{
			for (final Runnable r : this.commands) r.run();
			ReactiveAudit.this.statLow.reset();
			ReactiveAudit.this.statMedium.reset();
			ReactiveAudit.this.statLow.reset();
		}
	}
}
