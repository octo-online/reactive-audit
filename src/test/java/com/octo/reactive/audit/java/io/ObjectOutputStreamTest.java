package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class ObjectOutputStreamTest extends FileOutputStreamTest
{
	@Override
	protected OutputStream newOutputStream() throws IOException
	{
		return new ObjectOutputStream(super.newOutputStream());
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
		};
		new Derived();
	}
}
