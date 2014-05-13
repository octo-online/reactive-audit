package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class LineNumberReaderTest extends FileReaderTest
{
	@Override
	protected Reader newReader() throws IOException
	{
		return new LineNumberReader(super.newReader());
	}
	@Test
	public void derived() throws IOException
	{
		class Derived extends LineNumberReader
		{
			Derived() throws IOException
			{
				super(new StringReader("abc"));
			}
		};
		new Derived();
	}
}
