package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteArrayInputStreamTest extends InputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		return new ByteArrayInputStream(new byte[10]);
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends ByteArrayInputStream
		{
			Derived()
			{
				super(new byte[10]);
			}
		}
		;
		new Derived();
	}
}
