package com.octo.reactive.audit;

import static com.octo.reactive.audit.Logger.Level.*;
import static com.octo.reactive.audit.Logger.Level.Error;

/**
 * Created by pprados on 19/05/2014.
 */
class Logger
{
	private static final String[] levelsString = new String[]
			{
					"",
					"ERROR: ",
					"WARN : ",
					"INFO : ",
					"DEBUG: "
			};
	DelegateLogger delegate =
			(level, msg) -> System.err.println(levelsString[level.ordinal()] + msg);  // FIXME;
	private Level logLevel = None;

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
		log(Error, msg.toString());
	}

	public void error(String format, String... args)
	{
		log(Error, String.format(format, args));
	}

	public void warn(Object msg)
	{
		log(Warn, msg.toString());
	}

	public void warn(String format, String... args)
	{
		log(Warn, String.format(format, args));
	}

	public void info(Object msg)
	{
		log(Info, msg.toString());
	}

	public void info(String format, String... args)
	{
		log(Info, String.format(format, args));
	}

	public void debug(Object msg)
	{
		log(Debug, msg.toString());
	}

	public void debug(String format, String... args)
	{
		log(Debug, String.format(format, args));
	}

	void log(Level level, Object msg)
	{
		// TODO: filtre sur level ?
		if (level.compareTo(logLevel) > 0)
			delegate.log(level, msg);
	}

	enum Level
	{
		None, Error, Warn, Info, Debug
	}

	@FunctionalInterface
	public interface DelegateLogger
	{
		void log(Level level, Object msg);
	}

}
