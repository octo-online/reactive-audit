package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class BufferedReaderTest extends FileReaderTest
{
	@Override
	protected Reader newReader() throws IOException
	{
		push();
		Reader reader = super.newReader();
		pop();
		return new BufferedReader(reader);
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends BufferedReader
		{
			Derived()
			{
				super(new StringReader("abc"));
			}
		}
		new Derived();
	}
}
