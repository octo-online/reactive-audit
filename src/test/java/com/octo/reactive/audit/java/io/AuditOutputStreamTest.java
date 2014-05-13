package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by pprados on 06/05/14.
 */
public abstract class AuditOutputStreamTest extends OutputStreamTest
{
	@Override
	@Test(expected=AuditReactiveException.class)
	public void New() throws IOException
	{
		super.New();
	}
	@Override
	@Test(expected=AuditReactiveException.class)
	public void flush() throws IOException
	{
		super.flush();
	}
	@Override
	@Test(expected=AuditReactiveException.class)
	public void close() throws IOException
	{
		super.close();
	}
	@Override
	@Test(expected=AuditReactiveException.class)
	public void write_b() throws IOException
	{
		super.write_b();
	}
	@Override
	@Test(expected=AuditReactiveException.class)
	public void write_B() throws IOException
	{
		super.write_B();
	}
	@Override
	@Test(expected=AuditReactiveException.class)
	public void write_Bii() throws IOException
	{
		super.write_Bii();
	}
}
