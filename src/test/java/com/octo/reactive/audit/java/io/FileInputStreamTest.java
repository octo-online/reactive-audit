package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInputStreamTest extends AuditedInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		return new FileInputStream(IOTestTools.getTempFile());
	}

	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void New() throws IOException
	{
		super.New();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void derived() throws IOException
	{
		class Derived extends FileInputStream
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
