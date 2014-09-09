package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.IOTestTools;
import org.junit.Test;

import java.io.*;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class ObjectInputStreamTest extends AuditedInputStreamTest
{

	@Override
	protected InputStream newInputStream() throws IOException
	{
		push();
		File f = IOTestTools.getTempFile();
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f));
		os.writeObject("");
		os.close();
		FileInputStream in = new FileInputStream(f);
		pop();
		return new ObjectInputStream(in);
	}

	@Override
	@Test
	public void New() throws IOException
	{
		super.New();
	}

	@Test
	public void derived() throws IOException
	{
		final ByteArrayOutputStream buf = new ByteArrayOutputStream(100);
		ObjectOutputStream obj = new ObjectOutputStream(buf);
		obj.writeObject("");
		obj.close();
		class Derived extends ObjectInputStream
		{
			Derived() throws IOException
			{
				super(new ByteArrayInputStream(buf.toByteArray()));
			}
		}
		new Derived();
	}

}
