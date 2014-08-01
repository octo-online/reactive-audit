package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

// FIXME
public class PipedInputStreamTest //extends InputStreamTest
{
	//@Override
	protected InputStream newInputStream()
	{
		return null; // TODO : Pipe
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends OutputStreamWriter
		{
			Derived()
			{
				super(new ByteArrayOutputStream(10));
			}
		}
		new Derived();
	}
}
