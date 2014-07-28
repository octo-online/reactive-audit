package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class StringReaderTest extends ReaderTest
{
	@Override
	protected Reader newReader() throws IOException
	{
		return new StringReader("hello");
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends StringReader
		{
			Derived() throws IOException
			{
				super("abc");
			}
		}
		;
		new Derived();
	}
}
