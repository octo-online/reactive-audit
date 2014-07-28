package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStreamTest extends AuditOutputStreamTest
{
	@Override
	protected OutputStream newOutputStream() throws IOException
	{
		return new FileOutputStream(IOTestTools.getTempFile());
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
		class Derived extends FileOutputStream
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
