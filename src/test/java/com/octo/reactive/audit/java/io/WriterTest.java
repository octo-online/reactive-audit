package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public abstract class WriterTest
{
	protected abstract Writer newWriter() throws IOException ;

	@Test
	public void New() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		try (Writer writer=newWriter())
		{
			ConfigAuditReactive.off.commit();
		}

	}
	@Test
	public void append_c() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.append('c');
		}
	}
	@Test
	public void append_CharSequence() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.append("abc");
		}
	}
	@Test
	public void append_CharSequence_ii() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.append("abc",0,1);
		}
	}
	@Test
	public void close() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.close();
		}
	}
	@Test
	public void flush() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.flush();
		}
	}
	@Test
	public void write_C() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.write(new char[1]);
		}
	}
	@Test
	public void write_Cii() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.write(new char[1],0,1);
		}
	}
	@Test
	public void write_i() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.write(0);
		}
	}
	@Test
	public void write_String() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.write("abc");
		}
	}
	@Test
	public void write_String_ii() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.write("abc",0,1);
		}
	}
}
