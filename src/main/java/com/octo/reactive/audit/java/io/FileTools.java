package com.octo.reactive.audit.java.io;

import java.io.*;
import java.lang.reflect.Field;

/**
 * Created by pprados on 28/05/2014.
 */
public class FileTools
{
	static final Field fieldInFilterInputStream;
	static final Field fieldBinObjectInputStream;
	static final Field fieldInObjectInputStream;
	static final Field fieldPeekObjectInputStream;


	static
	{
		try
		{
			fieldInFilterInputStream = FilterInputStream.class.getDeclaredField("in");
			fieldInFilterInputStream.setAccessible(true);
			fieldBinObjectInputStream = ObjectInputStream.class.getDeclaredField("bin");
			fieldBinObjectInputStream.setAccessible(true);
			fieldInObjectInputStream = fieldBinObjectInputStream.getType().getDeclaredField("in");
			fieldInObjectInputStream.setAccessible(true);
			fieldPeekObjectInputStream = fieldInObjectInputStream.getType().getDeclaredField("in");
			fieldPeekObjectInputStream.setAccessible(true);
		}
		catch (NoSuchFieldException e)
		{
			throw new Error(e);
		}
	}

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

	static public boolean isLastInputStreamWithLatency(InputStream in)
	{
		while (in instanceof FilterInputStream
				|| in instanceof ObjectInputStream)
		{
			try
			{
				if (in instanceof FilterInputStream)
				{
					FilterInputStream filter = (FilterInputStream) in;
					in = (InputStream) fieldInFilterInputStream.get(filter);
				}
				else
				{
					ObjectInputStream objIn = (ObjectInputStream) in;
					// Ok for Java8
					in = (InputStream) fieldPeekObjectInputStream.get(
							fieldInObjectInputStream.get(
									fieldBinObjectInputStream.get(objIn)
							)
					);
				}
			}
			catch (IllegalAccessException e)
			{
				throw new Error(e);
			}
		}
		return (in instanceof FileInputStream);
	}

	public static boolean isLastInputStreamInReaderWithLatency(Reader reader)
	{
		try
		{
			while (reader instanceof FilterReader
					|| reader instanceof BufferedReader)
			{
				if (reader instanceof FilterReader)
				{
					reader = (Reader) fieldInFilterReader.get(reader);
				}
				else
				{
					reader = (Reader) fieldInBufferedReader.get(reader);
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
