package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class PushbackInputStreamTest extends FileInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		push();
		InputStream in = super.newInputStream();
		pop();
		return new PushbackInputStream(in);
	}

	@Override
	@Test
	public void New() throws IOException
	{
		super.New();
	}

	@Test
	@Override
	public void derived() throws IOException
	{
		class Derived extends PrintWriter
		{
			Derived() throws IOException
			{
				super(new ByteArrayOutputStream(10));
			}
		}
		;
		new Derived();
	}
}
