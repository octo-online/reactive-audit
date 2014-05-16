package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 06/05/14.
 */
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
			Derived() throws IOException
			{
				super(new StringReader("abc"));
			}
		};
		new Derived();
	}
}
