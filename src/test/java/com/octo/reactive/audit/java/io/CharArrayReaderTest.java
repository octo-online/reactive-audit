package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;

public class CharArrayReaderTest extends ReaderTest
{
	@Override
	protected Reader newReader() throws IOException
	{
		return new CharArrayReader(new char[1]);
	}

	@Test
	public void derived()
	{
		class Derived extends CharArrayReader
		{
			Derived()
			{
				super(new char[10]);
			}
		}
		new Derived();
	}
}
