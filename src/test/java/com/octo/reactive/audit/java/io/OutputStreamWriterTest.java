package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;
import static com.octo.reactive.audit.TestTools.*;


import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class OutputStreamWriterTest extends AuditedWriterTest
{
	@Override
	protected Writer newWriter() throws IOException
	{
		push();
		OutputStream out=new FileOutputStream(getFileOut());
		pop();
		return new OutputStreamWriter(out);

	}
	// FIXME: monter de classe ?
	protected File getFileOut() throws IOException
	{
		push();
		File f=File.createTempFile("temp-file-name", ".tmp");
		f.delete();
		f.deleteOnExit();
		pop();
		return f;
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
		};
		new Derived();
	}
}
