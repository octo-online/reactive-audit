package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 11/05/2014.
 */
public class FilterInputStreamTest extends FileInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		push();
		InputStream in = super.newInputStream();
		pop();
		return new BufferedInputStream(in);
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
		class Derived extends FilterInputStream
		{
			Derived() throws IOException
			{
				super(newInputStream());
			}
		};
		new Derived();
	}

}
