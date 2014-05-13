package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class PushbackInputStreamTest extends FileInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		return null; // TODO
	}

//	@Test
//	public void derived() throws IOException
//	{
//		class Derived extends PrintWriter
//		{
//			Derived() throws IOException
//			{
//				super(new ByteArrayOutputStream(10));
//			}
//		};
//		new Derived();
//	}
}
