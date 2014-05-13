package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;

import java.io.*;
import java.lang.reflect.Field;

public class AbstractReaderAudit extends AbstractInputStreamAudit
{
	static final Field fieldLockReader;
	static final Field fieldInFilterReader;
	static final Field fieldInBufferedReader;


	static
	{
		try
		{
			fieldLockReader = Reader.class.getDeclaredField("lock");
			fieldLockReader.setAccessible(true);
			fieldInFilterReader = FilterReader.class.getDeclaredField("in");
			fieldInFilterReader.setAccessible(true);
			fieldInBufferedReader = BufferedReader.class.getDeclaredField("in");
			fieldInBufferedReader.setAccessible(true);

		}
		catch (NoSuchFieldException e)
		{
			throw new Error(e);
		}
	}

	protected boolean isLastInputStreamInReaderWithLatency(JoinPoint thisJoinPoint)
	{
		try
		{
			Reader reader = (Reader) thisJoinPoint.getTarget();
			while (reader instanceof FilterReader
					|| reader instanceof BufferedReader)
			{
				if (reader instanceof FilterReader)
				{
					reader=(Reader)fieldInFilterReader.get(reader);
				}
				else
				{
					reader=(Reader)fieldInBufferedReader.get(reader);
				}
			}
			if (reader instanceof InputStreamReader)
			{
				InputStream in = (InputStream) fieldLockReader.get(reader);
				return isLastInputStreamWithLatency(in);
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