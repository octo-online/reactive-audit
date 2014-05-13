package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class ByteArrayInputStreamTest extends InputStreamTest
{
	@Override
	protected InputStream newInputStream()
	{
		return new ByteArrayInputStream(new byte[10]);
	}

	@Test
	public void derived()
	{
		class Derived extends ByteArrayInputStream
		{
			Derived()
			{
				super(new byte[10]);
			}
		};
		new Derived();
	}
}
