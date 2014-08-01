package com.octo.reactive.audit;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class AuditLogFormat extends Formatter
{

	private final Date dat = new Date();
	private final String format;

	AuditLogFormat(String format)
	{
		this.format = format;
	}

	public synchronized String format(LogRecord record)
	{
		dat.setTime(record.getMillis());
		String source;
		if (record.getSourceClassName() != null)
		{
			source = record.getSourceClassName();
			if (record.getSourceMethodName() != null)
			{
				source += " " + record.getSourceMethodName();
			}
		}
		else
		{
			source = record.getLoggerName();
		}
		String message = formatMessage(record);
		String throwable = "";
		if (record.getThrown() != null)
		{
			AuditReactive.config.incSuppress();
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			pw.println();
			record.getThrown().printStackTrace(pw);
			pw.close();
			throwable = sw.toString();
			AuditReactive.config.decSuppress();
		}
		return String.format(format,
		                     dat,
		                     source,
		                     record.getLoggerName(),
		                     record.getLevel().getName(),
		                     message,
		                     throwable);
	}

	String getFormat()
	{
		return format;
	}
}

