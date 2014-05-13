package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
@Deprecated
@SuppressWarnings( "deprecation" )
public class LineNumberInputStreamTest extends FileInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		return new LineNumberInputStream(super.newInputStream());
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends LineNumberInputStream
		{
			Derived() throws IOException
			{
				super(new ByteArrayInputStream(new byte[10]));
			}
		};
		new Derived();
	}
}
