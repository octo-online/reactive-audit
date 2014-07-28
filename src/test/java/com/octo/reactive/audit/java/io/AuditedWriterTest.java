package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;

public abstract class AuditedWriterTest extends WriterTest
{
	@Override
	protected abstract Writer newWriter() throws IOException;

	// FIXME
//	@Test(expected=AuditReactiveException.class)
//	public void New() throws IOException
//	{
//		super.New();
//	}
	@Test(expected = FileAuditReactiveException.class)
	public void append_c() throws IOException
	{
		super.append_c();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void append_CharSequence() throws IOException
	{
		super.append_CharSequence();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void append_CharSequence_ii() throws IOException
	{
		super.append_CharSequence_ii();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void close() throws IOException
	{
		super.close();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void flush() throws IOException
	{
		super.flush();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_C() throws IOException
	{
		super.write_C();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_Cii() throws IOException
	{
		super.write_Cii();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_i() throws IOException
	{
		super.write_i();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_String() throws IOException
	{
		super.write_String();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void write_String_ii() throws IOException
	{
		super.write_String_ii();
	}
}
