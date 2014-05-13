package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class StringWriterTest extends WriterTest
{
	@Override
	protected Writer newWriter() throws IOException
	{
		return new StringWriter(10);
	}
	@Test
	public void derived() throws IOException
	{
		class Derived extends StringWriter
		{
			Derived() throws IOException
			{
				super(10);
			}
		};
		new Derived();
	}
}
