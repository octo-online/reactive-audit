package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;

import java.io.*;
import java.lang.reflect.Field;

public class AbstractWriterAudit extends AbstractOutputStreamAudit
{
	static final Field fieldLockWriter;
	static final Field fieldOutFilterWriter;
	static final Field fieldOutBufferedWriter;
	static final Field fieldOutPrintWriter;


	static
	{
		try
		{
			fieldLockWriter = Writer.class.getDeclaredField("lock");
			fieldLockWriter.setAccessible(true);
			fieldOutFilterWriter = FilterWriter.class.getDeclaredField("out");
			fieldOutFilterWriter.setAccessible(true);
			fieldOutBufferedWriter = BufferedWriter.class.getDeclaredField("out");
			fieldOutBufferedWriter.setAccessible(true);
			fieldOutPrintWriter = PrintWriter.class.getDeclaredField("out");
			fieldOutPrintWriter.setAccessible(true);

		}
		catch (NoSuchFieldException e)
		{
			throw new Error(e);
		}
	}

	protected boolean isLastOutputStreamFromWriterWithLatency(JoinPoint thisJoinPoint)
	{
		try
		{
			Writer writer = (Writer) thisJoinPoint.getTarget();
			while (writer instanceof FilterWriter
					|| writer instanceof BufferedWriter
					|| writer instanceof PrintWriter)
			{
				if (writer instanceof FilterWriter)
				{
					writer=(Writer) fieldOutFilterWriter.get(writer);
				}
				else if (writer instanceof PrintWriter)
				{
					writer=(Writer)fieldOutPrintWriter.get(writer);
				}
				else
				{
					writer=(Writer) fieldOutBufferedWriter.get(writer);
				}
			}
			if (writer instanceof OutputStreamWriter)
			{
				OutputStream out = (OutputStream) fieldLockWriter.get(writer);
				return isLastOutputStreamWithLatency(out);
			}
			else
				return false;
		}
		catch (IllegalAccessException e)
		{
			throw new Error(e);
		}
	}
}