package com.octo.reactive.audit;

/**
 * Created by pprados on 19/05/2014.
 */
class Logger
{
	@FunctionalInterface
	public interface DelegateLogger
	{
		void log(int level, String msg);
	}

	public static final int            None     = 0;// FIXME: 0;
	public static final int            Error    = 1;
	public static final int            Warn     = 2;
	public static final int            Info     = 3;
	public static final int            Debug    = 4;
	static final String[] levels = new String[]
			{
					"",
					"ERROR: ",
					"WARN : ",
					"INFO : ",
					"DEBUG: "
			};


	DelegateLogger delegate =
			(level, msg) -> System.err.println(levels[level] + msg);  // FIXME

	private int logLevel = None;

	void setLogLevel(int aLevel)
	{
		logLevel = aLevel;
	}

	int getLogLevel()
	{
		return logLevel;
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

	void log(int level, String msg)
	{
		// TODO: filtre sur level ?
		if (level>=logLevel)
			delegate.log(level,msg);
	}

}
