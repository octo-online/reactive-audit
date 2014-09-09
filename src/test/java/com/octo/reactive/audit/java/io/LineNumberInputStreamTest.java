package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

@Deprecated
public class LineNumberInputStreamTest extends FileInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		push();
		InputStream in = super.newInputStream();
		pop();
		return new java.io.LineNumberInputStream(in);
	}

	@Override
	@Test
	public void New() throws IOException
	{
		super.New();
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends java.io.LineNumberInputStream
		{
			Derived()
			{
				super(new ByteArrayInputStream(new byte[10]));
			}
		}
		new Derived();
	}
}
