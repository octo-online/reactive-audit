package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by pprados on 06/05/14.
 */
public class FileWriterTest extends OutputStreamWriterTest
{
	@Override
	protected Writer newWriter() throws IOException
	{
		return new FileWriter(IOTestTools.getTempFile());
	}

	@Test(expected = AuditReactiveException.class)
	public void New() throws IOException
	{
		super.New();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void derived() throws IOException
	{
		class Derived extends FileWriter
		{
			Derived() throws IOException
			{
				super(IOTestTools.getTempFile());
			}
		}
		;
		new Derived();
	}
}
