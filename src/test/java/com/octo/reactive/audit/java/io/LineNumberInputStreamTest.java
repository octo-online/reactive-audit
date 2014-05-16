package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

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
		push();
		InputStream in = super.newInputStream();
		pop();
		return new LineNumberInputStream(in);
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
