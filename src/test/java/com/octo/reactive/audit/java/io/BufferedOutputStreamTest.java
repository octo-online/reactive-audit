package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import org.junit.Test;

import java.io.*;

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
