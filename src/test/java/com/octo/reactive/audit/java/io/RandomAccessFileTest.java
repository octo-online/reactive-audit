package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest
{
	private RandomAccessFile newRandomAccessFile() throws IOException
	{
		return new RandomAccessFile(IOTestTools.getTempFile(), "rw");
	}

	@Test(expected = FileAuditReactiveException.class)
	public void new_file() throws IOException
	{
		AuditReactive.strict.commit();
		try (RandomAccessFile rw = new RandomAccessFile(IOTestTools.getTempFile(), "rw"))
		{
			AuditReactive.off.commit();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void new_string() throws IOException
	{
		AuditReactive.strict.commit();
		try (RandomAccessFile rw = new RandomAccessFile(IOTestTools.getTempFile().getAbsoluteFile(), "rw"))
		{
			AuditReactive.off.commit();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void read() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.read();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void read_B() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.read(new byte[1]);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void read_Bii() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.read(new byte[1], 0, 1);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readLine() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readLine();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readBoolean() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readBoolean();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readByte() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readByte();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readChar() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readChar();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readDouble() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readDouble();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readFloat() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readFloat();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readFully() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readFully(new byte[1]);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readInt() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readInt();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readLong() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readLong();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readShort() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readShort();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readUnsignedByte() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readUnsignedByte();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readUnsignedShort() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readUnsignedShort();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void readUTF() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.readUTF();
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_B() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.write(new byte[1]);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_Bii() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.write(new byte[1], 0, 1);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_i() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.write(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeBoolean() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.writeBoolean(true);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeByte() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.writeByte(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeBytes() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.writeBytes("");
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeChar() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.writeChar(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeChars() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.writeChars("");
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeDouble() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.writeDouble(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeFloat() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.writeFloat(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeShort() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.writeShort(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void writeUTF() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.writeUTF("");
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void seek() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.seek(0);
		}

	}

	@Test(expected = FileAuditReactiveException.class)
	public void close() throws IOException
	{
		AuditReactive.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			AuditReactive.strict.commit();
			rw.close();
		}

	}
}
