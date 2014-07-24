package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class CharArrayWriterTest extends WriterTest
{
	@Override
	protected Writer newWriter() throws IOException
	{
		return new CharArrayWriter(10);
	}
	@Test
	public void derived()
	{
		class Derived extends CharArrayWriter
		{
			Derived()
			{
				super(10);
			}
		};
		new Derived();
	}
}
