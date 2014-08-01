package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class LineNumberReaderTest extends FileReaderTest
{
	@Override
	protected Reader newReader() throws IOException
	{
		push();
		Reader in = super.newReader();
		pop();
		return new LineNumberReader(in);
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends LineNumberReader
		{
			Derived()
			{
				super(new StringReader("abc"));
			}
		}
		new Derived();
	}
}
