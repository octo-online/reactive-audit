package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 06/05/14.
 */
public class BufferedOutputStreamTest extends FileOutputStreamTest
{
	@Override
	protected OutputStream newOutputStream() throws IOException
	{
		push();
		OutputStream out = super.newOutputStream();
		pop();
		return new BufferedOutputStream(out);
	}
	@Override
	@Test
	public void New() throws IOException
	{
		super.New();
	}

	@Test
	public void derived()
	{
		class Derived extends BufferedOutputStream
		{
			Derived()
			{
				super(new ByteArrayOutputStream(10));
			}
		};
		new Derived();
	}
}
