package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class BufferedOutputStreamTest extends FileOutputStreamTest
{
	@Override
	protected OutputStream newOutputStream() throws IOException
	{
		return new BufferedOutputStream(super.newOutputStream());
	}

	@Test
	public void derived()
	{
		class Derived extends BufferedOutputStream
		{
			Derived()
			{
				super(new ByteArrayOutputStream(10));
			}
		};
		new Derived();
	}
}
