package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import org.junit.Test;

import java.io.*;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 06/05/14.
 */
public class PushbackInputStreamTest extends FileInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		push();
		InputStream in=super.newInputStream();
		pop();
		return new PushbackInputStream(in);
	}
	@Override
	@Test
	public void New() throws IOException
	{
		super.New();
	}

	@Test
	public void derived_2() throws IOException
	{
		class Derived extends PrintWriter
		{
			Derived() throws IOException
			{
				super(new ByteArrayOutputStream(10));
			}
		};
		new Derived();
	}
}
