package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class ObjectOutputStreamTest extends FileOutputStreamTest
{
	@Override
	protected OutputStream newOutputStream() throws IOException
	{
		push();
		OutputStream out = super.newOutputStream();
		pop();
		return new ObjectOutputStream(out);
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
		class Derived extends ObjectOutputStream
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
