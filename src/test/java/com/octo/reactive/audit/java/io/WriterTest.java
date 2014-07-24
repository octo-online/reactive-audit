package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactive;
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
		AuditReactive.strict.commit();
		try (Writer writer=newWriter())
		{
			AuditReactive.off.commit();
		}

	}
	@Test
	public void append_c() throws IOException
	{
		AuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			AuditReactive.strict.commit();
			out.append('c');
		}
	}
	@Test
	public void append_CharSequence() throws IOException
	{
		AuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			AuditReactive.strict.commit();
			out.append("abc");
		}
	}
	@Test
	public void append_CharSequence_ii() throws IOException
	{
		AuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			AuditReactive.strict.commit();
			out.append("abc",0,1);
		}
	}
	@Test
	public void close() throws IOException
	{
		AuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			AuditReactive.strict.commit();
			out.close();
		}
	}
	@Test
	public void flush() throws IOException
	{
		AuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			AuditReactive.strict.commit();
			out.flush();
		}
	}
	@Test
	public void write_C() throws IOException
	{
		AuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			AuditReactive.strict.commit();
			out.write(new char[1]);
		}
	}
	@Test
	public void write_Cii() throws IOException
	{
		AuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			AuditReactive.strict.commit();
			out.write(new char[1],0,1);
		}
	}
	@Test
	public void write_i() throws IOException
	{
		AuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			AuditReactive.strict.commit();
			out.write(0);
		}
	}
	@Test
	public void write_String() throws IOException
	{
		AuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			AuditReactive.strict.commit();
			out.write("abc");
		}
	}
	@Test
	public void write_String_ii() throws IOException
	{
		AuditReactive.off.commit();
		try (Writer out= newWriter())
		{
			AuditReactive.strict.commit();
			out.write("abc",0,1);
		}
	}
}
