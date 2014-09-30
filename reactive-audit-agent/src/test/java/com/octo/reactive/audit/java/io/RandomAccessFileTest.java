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

package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest
{
	private RandomAccessFile newRandomAccessFile()
			throws IOException
	{
		return new RandomAccessFile(IOTestTools.getTempFile(), "rw");
	}

	@Test(expected = FileReactiveAuditException.class)
	public void new_file()
			throws IOException
	{
		TestTools.strict.commit();
		try (RandomAccessFile rw = new RandomAccessFile(IOTestTools.getTempFile(), "rw"))
		{
			ReactiveAudit.off.commit();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void new_string()
			throws IOException
	{
		TestTools.strict.commit();
		try (RandomAccessFile rw = new RandomAccessFile(IOTestTools.getTempFile().getAbsoluteFile(), "rw"))
		{
			ReactiveAudit.off.commit();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void read()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.read();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void read_B()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.read(new byte[1]);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void read_Bii()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.read(new byte[1], 0, 1);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readLine()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readLine();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readBoolean()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readBoolean();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readByte()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readByte();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readChar()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readChar();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readDouble()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readDouble();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readFloat()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readFloat();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readFully()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readFully(new byte[1]);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readInt()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readInt();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readLong()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readLong();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readShort()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readShort();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readUnsignedByte()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readUnsignedByte();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readUnsignedShort()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readUnsignedShort();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void readUTF()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.readUTF();
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void write_B()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.write(new byte[1]);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void write_Bii()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.write(new byte[1], 0, 1);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void write_i()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.write(0);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void writeBoolean()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.writeBoolean(true);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void writeByte()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.writeByte(0);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void writeBytes()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.writeBytes("");
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void writeChar()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.writeChar(0);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void writeChars()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.writeChars("");
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void writeDouble()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.writeDouble(0);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void writeFloat()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.writeFloat(0);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void writeShort()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.writeShort(0);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void writeUTF()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.writeUTF("");
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void seek()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.seek(0);
		}

	}

	@Test(expected = FileReactiveAuditException.class)
	public void close()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (RandomAccessFile rw = newRandomAccessFile())
		{
			TestTools.strict.commit();
			rw.close();
		}

	}
}
