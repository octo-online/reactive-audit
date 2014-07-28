package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.IOTestTools;
import org.junit.Test;

import java.io.*;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class OutputStreamWriterTest extends AuditedWriterTest
{
	@Override
	protected Writer newWriter() throws IOException
	{
		push();
		OutputStream out = new FileOutputStream(IOTestTools.getTempFile());
		pop();
		return new OutputStreamWriter(out);

	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends OutputStreamWriter
		{
			Derived() throws IOException
			{
				super(new ByteArrayOutputStream(10));
			}
		}
		;
		new Derived();
	}
}
