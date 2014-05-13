package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;

import java.io.*;
import java.lang.reflect.Field;

public class AbstractInputStreamAudit extends AbstractAudit
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
			fieldInObjectInputStream =fieldBinObjectInputStream.getType().getDeclaredField("in");
			fieldInObjectInputStream.setAccessible(true);
			fieldPeekObjectInputStream =fieldInObjectInputStream.getType().getDeclaredField("in");
			fieldPeekObjectInputStream.setAccessible(true);
		}
		catch (NoSuchFieldException e)
		{
			throw new Error(e);
		}
	}

	protected boolean isLastInputStreamWithLatency(InputStream in)
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
					ObjectInputStream objIn=(ObjectInputStream)in;
					// Ok for Java8
					in=(InputStream)fieldPeekObjectInputStream.get(
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

}