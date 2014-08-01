package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.IOTestTools;
import org.junit.Test;

import java.io.*;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class BufferedWriterTest extends FileWriterTest
{
	@Override
	protected Writer newWriter() throws IOException
	{
		push();
		FileOutputStream out = new FileOutputStream(IOTestTools.getTempFile());
		Writer writer = new OutputStreamWriter(out);
		pop();
		return new BufferedWriter(writer);
	}

	@Test
	public void New() throws IOException
	{
		super.New();
	}

	@Test
	public void derived()
	{
		class Derived extends BufferedWriter
		{
			Derived()
			{
				super(new StringWriter(10));
			}
		}
		new Derived();
	}
}
