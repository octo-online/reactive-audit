package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.*;
import static com.octo.reactive.audit.TestTools.*;

/**
 * Created by pprados on 06/05/14.
 */
public class BufferedWriterTest extends FileWriterTest
{
	@Override
	protected Writer newWriter() throws IOException
	{
		push();
		FileOutputStream out = new FileOutputStream(getFileOut());
		Writer writer=new OutputStreamWriter(out);
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
		};
		new Derived();
	}
}
