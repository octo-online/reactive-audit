package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pprados on 06/05/14.
 */
public class BufferedInputStreamTest extends FileInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		return new BufferedInputStream(super.newInputStream());
	}

	@Test
	public void derived()
	{
		class Derived extends BufferedInputStream
		{
		  public Derived()
		  {
			  super(new ByteArrayInputStream(new byte[10]));
		  }
		};
		new Derived();
	}
}
