package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class SequenceInputStreamTest extends FileInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		return new SequenceInputStream(super.newInputStream(),super.newInputStream());
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends SequenceInputStream
		{
			Derived() throws IOException
			{
				super(new ByteArrayInputStream(new byte[10]),new ByteArrayInputStream(new byte[10]));
			}
		};
		new Derived();
	}
}
