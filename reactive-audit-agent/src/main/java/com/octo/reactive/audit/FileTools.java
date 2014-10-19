/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;

@SuppressWarnings({"FinalClass", "UtilityClass"})
public final class FileTools
{
	public static final int NO_ERROR   = 0;
	public static final int NET_ERROR  = -1;
	public static final int FILE_ERROR = 1;
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

	static final FilenameDumpClosure FileTools_filenameDump      = new FilenameDumpClosure()
	{
		@Override
		public void dump(StringBuilder buf, Class cl, String filename)
		{
			filenameDump(buf, cl, filename);
		}
	};
	static final FilenameDumpClosure FileTools_chainFilenameDump = new FilenameDumpClosure()
	{
		@Override
		public void dump(StringBuilder buf, Class cl, String filename)
		{
			chainFilenameDump(buf, cl, filename);
		}
	};
	private static final Field fieldInFilterInputStream;
	private static final Field fieldBinObjectInputStream;
	private static final Field fieldInObjectInputStream;
	private static final Field fieldPeekObjectInputStream;
	private static final Field fieldLockReader;
	private static final Field fieldInFilterReader;
	private static final Field fieldInBufferedReader;
	private static final Field fieldInFileDescriptor;

	static
	{
		try
		{
			fieldInFileDescriptor = FileDescriptor.class.getDeclaredField("fd");
			fieldInFileDescriptor.setAccessible(true);
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

	private static final Field fieldLockWriter;
	private static final Field fieldOutFilterWriter;
	private static final Field fieldOutBufferedWriter;
	private static final Field fieldOutPrintWriter;

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

	private static Field fieldPathOutputStream = null;
	private static Field fieldPathInputStream  = null;

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
			try
			{
				fieldPathOutputStream = FileOutputStream.class.getDeclaredField("path");
				fieldPathOutputStream.setAccessible(true);
			}
			catch (Exception e)
			{
				// Ignore
			}
			try
			{
				fieldPathInputStream = FileInputStream.class.getDeclaredField("path");
				fieldPathInputStream.setAccessible(true);
			}
			catch (Exception e)
			{
				// Ignore
			}
		}
		catch (NoSuchFieldException e)
		{
			throw new Error(e);
		}
	}

	private FileTools()
	{

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
		if (out.getClass().getName().equals("java.net.SocketOutputStream")) return NET_ERROR;
		if (out instanceof FileOutputStream)
		{
			try
			{
				int i = (Integer) fieldInFileDescriptor.get(((FileOutputStream) out).getFD());
				if ((i == 1) || (i == 2))
				{
					return NO_ERROR; // Console
				}
			}
			catch (Exception e)
			{
				// Ignore
			}
			return FILE_ERROR;
		}
		if (out.getClass().getName().startsWith("sun.net.www.")) return NET_ERROR;
		return NO_ERROR;
	}

	@SuppressWarnings("ChainOfInstanceofChecks")
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
			}
			if (writer instanceof OutputStreamWriter)
			{
				OutputStream out = (OutputStream) fieldLockWriter.get(writer);
				return isLastOutputStreamWithLatency(out);
			}
			else
			{
				return NO_ERROR;
			}
		}
		catch (IllegalAccessException e)
		{
			throw new Error(e);
		}
	}

	public static CharSequence printFilename(OutputStream out)
	{
		// Java8 return dumpChain(out, new StringBuilder(), FileTools::filenameDump);
		return dumpChain(out, new StringBuilder(), FileTools_filenameDump);
	}

	public static CharSequence printFilename(Writer writer)
	{
		// Java8 return dumpChain(writer, FileTools::filenameDump);
		return dumpChain(writer, FileTools_filenameDump);
	}

	static public CharSequence printFilename(InputStream in)
	{
		// Java8 return dumpChain(in, new StringBuilder(), FileTools::filenameDump);
		return dumpChain(in, new StringBuilder(), FileTools_filenameDump);
	}

	public static CharSequence printFilename(Reader reader)
	{
		// Java8 return dumpChain(reader, FileTools::filenameDump);
		return dumpChain(reader, FileTools_filenameDump);
	}

	static public CharSequence dumpFilename(InputStream in)
	{
		// Java8 return dumpChain(in, new StringBuilder(), FileTools::chainFilenameDump);
		return dumpChain(in, new StringBuilder(), FileTools_chainFilenameDump);
	}

	public static CharSequence dumpChain(OutputStream out)
	{
		// Java8 return dumpChain(out, new StringBuilder(), FileTools::chainFilenameDump);
		return dumpChain(out, new StringBuilder(), FileTools_chainFilenameDump);
	}

	public static CharSequence dumpChain(Writer writer)
	{
		// Java8 return dumpChain(writer, FileTools::chainFilenameDump);
		return dumpChain(writer, FileTools_chainFilenameDump);
	}

	static public CharSequence dumpChain(InputStream in)
	{
		// Java8 return dumpChain(in, new StringBuilder(), FileTools::chainFilenameDump);
		return dumpChain(in, new StringBuilder(), FileTools_chainFilenameDump);
	}

	public static CharSequence dumpChain(Reader reader)
	{
		// Java8 return dumpChain(reader, FileTools::chainFilenameDump);
		return dumpChain(reader, FileTools_chainFilenameDump);
	}


	private static CharSequence dumpChain(OutputStream out, StringBuilder buf, FilenameDumpClosure dump)
	{
		if (out == null) return buf;
		dump.dump(buf, out.getClass(), null);
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
				dump.dump(buf, out.getClass(), null);
			}
			catch (IllegalAccessException e)
			{
				throw new Error(e);
			}
		}
		if (out instanceof FileOutputStream)
		{
			String path;
			if (fieldPathOutputStream != null)
			{
				try
				{
					path = (String) fieldPathOutputStream.get(out);
					dump.dump(buf, null, path);
				}
				catch (IllegalAccessException e)
				{
					// Ignore
				}
			}
		}
		else
		{
			buf.append(" -> ").append(out.getClass().getSimpleName());
		}
		return buf;
	}

	@SuppressWarnings("ChainOfInstanceofChecks")
	public static CharSequence dumpChain(Writer writer, FilenameDumpClosure dump)
	{
		StringBuilder buf = new StringBuilder();
		if (writer == null) return buf;
		try
		{
			dump.dump(buf, writer.getClass(), null);
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
				dump.dump(buf, writer.getClass(), null);
			}
			if (writer instanceof OutputStreamWriter)
			{
				OutputStream out = (OutputStream) fieldLockWriter.get(writer);
				dump.dump(buf, Void.class, null);
				return dumpChain(out, buf, dump);
			}
			else
			{
				//if (out!=null) ConfigReactiveAudit.config.debug("Without delegate to output stream");
				return buf;
			}
		}
		catch (IllegalAccessException e)
		{
			throw new Error(e);
		}
	}

	static private CharSequence dumpChain(InputStream in,
										  StringBuilder buf,
										  FilenameDumpClosure dump)
	{
		if (in == null) return buf;
		dump.dump(buf, in.getClass(), null);
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
				dump.dump(buf, in.getClass(), null);
			}
			catch (IllegalAccessException e)
			{
				throw new Error(e);
			}
		}
		if (in instanceof FileInputStream)
		{
			String path;
			if (fieldPathInputStream != null)
			{
				try
				{
					path = (String) fieldPathInputStream.get(in);
					chainFilenameDump(buf, null, path);
				}
				catch (IllegalAccessException e)
				{
					// Ignore
				}
			}
		}
		else
		{
			dump.dump(buf, in.getClass(), null);
		}
		return buf;
	}

	private static CharSequence dumpChain(Reader reader, FilenameDumpClosure dump)
	{
		StringBuilder buf = new StringBuilder();
		if (reader == null) return buf;
		dump.dump(buf, reader.getClass(), null);
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
				dump.dump(buf, reader.getClass(), null);
			}
			if (reader instanceof InputStreamReader)
			{
				InputStream in = (InputStream) fieldLockReader.get(reader);
				dump.dump(buf, Void.class, null);
				return dumpChain(in, buf, dump);
			}
			return buf;
		}
		catch (IllegalAccessException e)
		{
			throw new Error(e);
		}
	}

	static private void chainFilenameDump(StringBuilder buf, Class cl, String filename)
	{
		if (cl != null)
		{
			buf.append(" -> ");
			if (cl != Void.class) buf.append(cl.getClass().getSimpleName());
		}
		if (filename != null)
		{
			buf.append(" -> ").append(filename);
		}
	}

	static private void filenameDump(StringBuilder buf, Class cl, String filename)
	{
		if (filename != null)
		{
			buf.append("with ").append(filename);
		}
	}

	static String homeFile(String filename)
	{
		if (filename.startsWith("file:"))
		{
			try
			{
				filename = new File(new URL(filename).toURI()).getPath();
			}
			catch (Exception e)
			{
				// Ignore
			}
		}
		String home = System.getProperty("user.home");
		String homeFilename;
		String homeRef = "~";
		if (File.separator.equals("\\")) // Windows
			homeRef = "%HOME%";
		if (filename.startsWith(home))
		{
			homeFilename = filename.substring(home.length());
			if (homeFilename.startsWith(File.separator))
				homeFilename = homeRef + homeFilename;
			else
				homeFilename = homeRef + File.separator + homeFilename;
		}
		else
			homeFilename = filename;
		return homeFilename;
	}

	interface FilenameDumpClosure
	{
		void dump(StringBuilder buf, Class cl, String filename);
	}
}
