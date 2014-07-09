package com.octo.reactive.audit.java.io;

import java.io.*;
import java.lang.reflect.Field;

/**
 * Created by pprados on 28/05/2014.
 */
public final class FileTools
{
	public static final int NO_ERROR   = 0;
	public static final int NET_ERROR  = -1;
	public static final int FILE_ERROR = 1;

	static final Field fieldInFilterInputStream;
	static final Field fieldBinObjectInputStream;
	static final Field fieldInObjectInputStream;
	static final Field fieldPeekObjectInputStream;
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

	static final Field fieldOutFilterOutputStream;
	static final Field fieldBoutObjectOutputStream;
	static final Field fieldOutObjectOutputStream;

	static
	{
		try
		{
			fieldOutFilterOutputStream = FilterOutputStream.class.getDeclaredField("out");
			fieldOutFilterOutputStream.setAccessible(true);
			fieldBoutObjectOutputStream = ObjectOutputStream.class.getDeclaredField("bout");
			fieldBoutObjectOutputStream.setAccessible(true);
			fieldOutObjectOutputStream = fieldBoutObjectOutputStream.getType().getDeclaredField("out");
			fieldOutObjectOutputStream.setAccessible(true);
		}
		catch (NoSuchFieldException e)
		{
			throw new Error(e);
		}
	}

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

	private FileTools()
	{

	}

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

	static public int isLastInputStreamWithLatency(InputStream in)
	{
		if (in == null)
			return NO_ERROR;
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
		if (in != null)
		{
			if (in.getClass().getName().equals("java.net.SocketInputStream")) return NET_ERROR;
			if (in instanceof FileInputStream) return FILE_ERROR;
			if (in.getClass().getName().startsWith("sun.net.www.")) return NET_ERROR;
		}
		return NO_ERROR;
	}

	public static int isLastReaderWithLatency(Reader reader)
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
				return NO_ERROR;
		}
		catch (IllegalAccessException e)
		{
			throw new Error(e);
		}
	}

	public static int isLastOutputStreamWithLatency(OutputStream out)
	{
		while (out instanceof FilterOutputStream
				|| out instanceof ObjectOutputStream)
		{
			try
			{
				if (out instanceof FilterOutputStream)
				{
					FilterOutputStream filter = (FilterOutputStream) out;
					out = (OutputStream) fieldOutFilterOutputStream.get(filter);
				}
				else
				{
					ObjectOutputStream objIn = (ObjectOutputStream) out;
					// Ok for Java8
					out = (OutputStream) fieldOutObjectOutputStream.get(
							fieldBoutObjectOutputStream.get(objIn)
					);
				}
			}
			catch (IllegalAccessException e)
			{
				throw new Error(e);
			}
		}
		//if (out != null) ConfigAuditReactive.config.logger.finest("FINAL OUT:" + out.getClass()); // FIXME
		if (out.getClass().getName().equals("java.net.SocketOutputStream")) return NET_ERROR;
		if (out instanceof FileOutputStream) return FILE_ERROR;
		if (out.getClass().getName().startsWith("sun.net.www.")) return NET_ERROR;
		return NO_ERROR;
	}

	public static int isLastOutputStreamFromWriterWithLatency(Writer writer)
	{
		try
		{
			while (writer instanceof FilterWriter
					|| writer instanceof BufferedWriter
					|| writer instanceof PrintWriter)
			{
				if (writer instanceof FilterWriter)
				{
					writer = (Writer) fieldOutFilterWriter.get(writer);
				}
				else if (writer instanceof PrintWriter)
				{
					writer = (Writer) fieldOutPrintWriter.get(writer);
				}
				else
				{
					writer = (Writer) fieldOutBufferedWriter.get(writer);
				}
				//if (writer!=null) ConfigAuditReactive.config.logger.finest("next writer instanceof "+writer.getClass());
			}
			if (writer instanceof OutputStreamWriter)
			{
				OutputStream out = (OutputStream) fieldLockWriter.get(writer);
				//if (out!=null) ConfigAuditReactive.config.debug("Delegate to output stream "+out.getClass());
				return isLastOutputStreamWithLatency(out);
			}
			else
			{
				//if (out!=null) ConfigAuditReactive.config.debug("Without delegate to output stream");
				return NO_ERROR;
			}
		}
		catch (IllegalAccessException e)
		{
			throw new Error(e);
		}
	}

	public static CharSequence dumpChain(OutputStream out)
	{
		return dumpChain(out, new StringBuilder());
	}

	private static CharSequence dumpChain(OutputStream out, StringBuilder buf)
	{
		if (out == null) return buf;
		buf.append(out.getClass().getName());
		while (out instanceof FilterOutputStream
				|| out instanceof ObjectOutputStream)
		{
			try
			{
				if (out instanceof FilterOutputStream)
				{
					FilterOutputStream filter = (FilterOutputStream) out;
					out = (OutputStream) fieldOutFilterOutputStream.get(filter);
				}
				else
				{
					ObjectOutputStream objIn = (ObjectOutputStream) out;
					// Ok for Java8
					out = (OutputStream) fieldOutObjectOutputStream.get(
							fieldBoutObjectOutputStream.get(objIn)
					);
				}
				buf.append(" -> " + out.getClass().getName());
			}
			catch (IllegalAccessException e)
			{
				throw new Error(e);
			}
		}
		return buf;
	}

	public static CharSequence dumpChain(Writer writer)
	{
		StringBuilder buf = new StringBuilder();
		if (writer == null) return buf;
		try
		{
			buf.append(writer.getClass().getName());
			while (writer instanceof FilterWriter
					|| writer instanceof BufferedWriter
					|| writer instanceof PrintWriter)
			{
				if (writer instanceof FilterWriter)
				{
					writer = (Writer) fieldOutFilterWriter.get(writer);
				}
				else if (writer instanceof PrintWriter)
				{
					writer = (Writer) fieldOutPrintWriter.get(writer);
				}
				else
				{
					writer = (Writer) fieldOutBufferedWriter.get(writer);
				}
				buf.append(" -> " + writer.getClass().getName());
			}
			if (writer instanceof OutputStreamWriter)
			{
				OutputStream out = (OutputStream) fieldLockWriter.get(writer);
				return dumpChain(out, buf.append(" -> "));
			}
			else
			{
				//if (out!=null) ConfigAuditReactive.config.debug("Without delegate to output stream");
				return buf;
			}
		}
		catch (IllegalAccessException e)
		{
			throw new Error(e);
		}
	}

	static public CharSequence dumpChain(InputStream in)
	{
		return dumpChain(in, new StringBuilder());
	}

	static private CharSequence dumpChain(InputStream in, StringBuilder buf)
	{
		if (in == null) return buf;
		buf.append(in.getClass().getName());
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
				buf.append(" -> " + in.getClass().getName());
			}
			catch (IllegalAccessException e)
			{
				throw new Error(e);
			}
		}
		return buf;
	}

	public static CharSequence dumpChain(Reader reader)
	{
		StringBuilder buf = new StringBuilder();
		if (reader == null) return buf;
		buf.append(reader.getClass().getName());
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
				buf.append(" -> " + reader.getClass().getName());
			}
			if (reader instanceof InputStreamReader)
			{
				InputStream in = (InputStream) fieldLockReader.get(reader);
				return dumpChain(in, buf.append(" -> "));
			}
			return buf;
		}
		catch (IllegalAccessException e)
		{
			throw new Error(e);
		}
	}

}
