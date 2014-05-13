package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class PrintStreamTest extends FilterOutputStreamTest
{
	@Override
	protected OutputStream newOutputStream() throws IOException
	{
		return new PrintStream(super.newOutputStream());
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends PrintStream
		{
			Derived() throws IOException
			{
				super(new ByteArrayOutputStream(10));
			}
		};
		new Derived();
	}
}
