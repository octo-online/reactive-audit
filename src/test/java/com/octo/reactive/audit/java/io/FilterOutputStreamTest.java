package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class FilterOutputStreamTest extends FileOutputStreamTest
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
	@Override
	public void derived() throws IOException
	{
		class Derived extends FilterOutputStream
		{
			Derived() throws IOException
			{
				super(newOutputStream());
			}
		}
		new Derived();
	}

}
