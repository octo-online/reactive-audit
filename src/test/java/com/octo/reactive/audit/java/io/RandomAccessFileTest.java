package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 06/05/14.
 */
public class RandomAccessFileTest
{
	// FIXME: monter de classe ?
	protected File getFileOut() throws IOException
	{
		push();
		File f = File.createTempFile("temp-file-name", ".tmp");
		f.delete();
		f.deleteOnExit();
		pop();
		return f;
	}

	protected RandomAccessFile newRandomAccessFile() throws IOException
	{
		return new RandomAccessFile(getFileOut(), "rw");
	}

	@Test(expected = FileAuditReactiveException.class)
	public void new_file() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		try (RandomAccessFile rw = new RandomAccessFile(getFileOut(), "rw"))
		{
			ConfigAuditReactive.off.commit();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void new_string() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		try (RandomAccessFile rw = new RandomAccessFile(getFileOut().getAbsoluteFile(), "rw"))
		{
			ConfigAuditReactive.off.commit();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void read() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.read();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void read_B() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.read(new byte[1]);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void read_Bii() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.read(new byte[1], 0, 1);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readLine() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readLine();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readBoolean() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readBoolean();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readByte() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readByte();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readChar() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readChar();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readDouble() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readDouble();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readFloat() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readFloat();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readFully() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readFully(new byte[1]);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readInt() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readInt();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readLong() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readLong();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readShort() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readShort();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readUnsignedByte() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readUnsignedByte();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readUnsignedShort() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readUnsignedShort();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readUTF() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.readUTF();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_B() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.write(new byte[1]);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_Bii() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.write(new byte[1], 0, 1);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_i() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.write(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeBoolean() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.writeBoolean(true);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeByte() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.writeByte(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeBytes() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.writeBytes("");
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeChar() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.writeChar(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeChars() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.writeChars("");
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeDouble() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.writeDouble(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeFloat() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.writeFloat(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeShort() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.writeShort(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeUTF() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.writeUTF("");
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void seek() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.seek(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void close() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			ConfigAuditReactive.strict.commit();
			rw.close();
		}

	}
}
