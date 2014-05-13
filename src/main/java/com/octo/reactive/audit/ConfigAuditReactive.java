package com.octo.reactive.audit;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by pprados on 07/05/14.
 */
// TODO : systemProperty 'some.prop', 'value'
// TODO : java.io.File
    // TODO: testu de cette classe
public class ConfigAuditReactive
{
	private static final String defaultThreadPattern="^(ForkJoinPool-.*|Test worker)";
	public static Logger  logger          = new Logger();
	private       boolean throwExceptions = false;
	private       Pattern threadPattern   = Pattern.compile(defaultThreadPattern);
	private ThreadLocal<Integer> suppressAudit=new ThreadLocal()
	{
		@Override protected Integer initialValue() {
			return 0;
		}
	};
	private Stack<Transaction> stack = new Stack<>();

	void incSuppress()
	{
		suppressAudit.set(suppressAudit.get() + 1);
	}
	void decSuppress()
	{
		suppressAudit.set(suppressAudit.get() - 1);
	}
	int getSuppress()
	{
		return suppressAudit.get();
	}
	public boolean isThrow()
	{
		//logger.warn("Throw exception");
		return throwExceptions;
	}
	public boolean isThreadNameMatch(String name)
	{
		return threadPattern.matcher(name).matches();
	}
	public boolean isSuppressAudit()
	{
		return suppressAudit.get()!=0;
	}
	public String getThreadPattern()
	{
		return threadPattern.toString();
	}

	private Transaction current()
	{
		return new Transaction()
				.log(logger.getLogLevel())
				.throwExceptions(throwExceptions)
				.threadPattern(threadPattern.toString());
	}
	protected void push()
	{
		stack.push(current());
	}
	protected void pop()
	{
		stack.pop().commit();
	}

	public class Transaction
	{
		private List<Runnable> cmds = new ArrayList<>();

		public Transaction throwExceptions(boolean onOff)
		{
			cmds.add(() -> throwExceptions = onOff);
			return this;
		}

		public Transaction log(int level)
		{
			cmds.add(() -> logger.setLevel(level));
			return this;
		}

		public Transaction threadPattern(@NotNull String pattern)
		{
			cmds.add(() -> threadPattern = Pattern.compile(pattern));
			return this;
		}

		public synchronized void commit()
		{
			cmds.forEach(x -> x.run());
		}
	}

	public Transaction begin()
	{
		return new Transaction();
	}

	public static final ConfigAuditReactive config  = new ConfigAuditReactive();
	public static final Transaction         strict  =
			config.begin()
					.throwExceptions(true)
					.log(Logger.None)
					.threadPattern(".*");
	public static final Transaction         logOnly =
			config.begin()
					.throwExceptions(false)
					.log(Logger.Warn)
					.threadPattern(defaultThreadPattern);
	public static final Transaction         off     =
			config.begin()
					.throwExceptions(false)
					.log(Logger.Error)
					.threadPattern("(?!)");
}
