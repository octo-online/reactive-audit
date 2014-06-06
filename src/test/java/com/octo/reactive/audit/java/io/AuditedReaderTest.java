package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by pprados on 06/05/14.
 */
public abstract class AuditedReaderTest extends ReaderTest
{
	// FIXME
//	@Override
//	@Test(expected=AuditReactiveException.class)
//	public void New() throws IOException
//	{
//		super.New();
//	}
	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void close() throws IOException
	{
		super.close();
	}

	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void read() throws IOException
	{
		super.read();
	}

	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void read_C() throws IOException
	{
		super.read_C();
	}

	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void read_Cii() throws IOException
	{
		super.read_Cii();
	}

	//	@Override  TODO CharBuffer
	//	@Test(expected=AuditReactiveException.class)
//	public void read_CharBuffer() throws IOException
//	{
//		super.read_CharBuffer();
//	}
	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void skip() throws IOException
	{
		super.skip();
	}
}
