package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class PrintStreamTest extends FilterOutputStreamTest
{
	@Override
	protected OutputStream newOutputStream() throws IOException
	{
		push();
		OutputStream out = super.newOutputStream();
		pop();
		return new PrintStream(out);
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends PrintStream
		{
			Derived() throws IOException
			{
				super(new ByteArrayOutputStream(10));
			}
		}
		;
		new Derived();
	}
}
