package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.IOException;

public abstract class AuditOutputStreamTest extends OutputStreamTest
{
	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void flush() throws IOException
	{
		super.flush();
	}

	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void close() throws IOException
	{
		super.close();
	}

	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void write_b() throws IOException
	{
		super.write_b();
	}

	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void write_B() throws IOException
	{
		super.write_B();
	}

	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void write_Bii() throws IOException
	{
		super.write_Bii();
	}
}
