package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import org.junit.Test;

import java.io.FileReader;
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
		return new FileWriter(getFileOut());
	}
	@Test(expected=AuditReactiveException.class)
	public void New() throws IOException
	{
		super.New();
	}

	@Test(expected=AuditReactiveException.class)
	public void derived() throws IOException
	{
		class Derived extends FileWriter
		{
			Derived() throws IOException
			{
				super(getFileOut());
			}
		};
		new Derived();
	}
}
