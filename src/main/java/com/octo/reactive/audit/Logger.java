package com.octo.reactive.audit;

import static com.octo.reactive.audit.Logger.Level.*;

/**
 * Created by pprados on 19/05/2014.
 */
class Logger
{
	DelegateLogger delegate = new StderrLogger();

	private Level logLevel = NONE;

	Level getLogLevel()
	{
		return logLevel;
	}

	void setLogLevel(Level aLevel)
	{
		logLevel = aLevel;
	}

	public void error(Object msg)
	{
		log(ERROR, msg.toString());
	}

	public void error(String format, Object... args)
	{
		log(ERROR, String.format(format, args));
	}

	public void warn(Object msg)
	{
		log(WARN, msg.toString());
	}

	public void warn(String format, Object... args)
	{
		log(WARN, String.format(format, args));
	}

	public void info(Object msg)
	{
		log(INFO, msg.toString());
	}

	public void info(String format, Object... args)
	{
		log(INFO, String.format(format, args));
	}

	public void debug(Object msg)
	{
		log(DEBUG, msg.toString());
	}

	public void debug(String format, Object... args)
	{
		log(DEBUG, String.format(format, args));
	}

	void log(Level level, CharSequence msg)
	{
		if (logLevel.compareTo(level) > 0)
			delegate.log(level, msg);
	}

	enum Level
	{
		NONE, ERROR, WARN, INFO, DEBUG
	}

	@FunctionalInterface
	public interface DelegateLogger
	{
		void log(Level level, CharSequence msg);
	}
	static class StderrLogger implements DelegateLogger
	{
		@Override
		public void log(Level level, CharSequence msg)
		{
			System.err.println(level.toString()+':'+msg); // FIXME: stdout ou file async ?
		}
	}
}
