package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;

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
		}
		;
		new Derived();
	}
}
