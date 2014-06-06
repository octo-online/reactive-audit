package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.*;
import java.util.Locale;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 06/05/14.
 */
public class PrintWriterTest extends OutputStreamWriterTest
{
	@Override
	protected PrintWriter newWriter() throws IOException
	{
		push();
		FileOutputStream out = new FileOutputStream(getFileOut());
		Writer writer = new OutputStreamWriter(out);
		pop();
		return new PrintWriter(writer);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void format_Locale_String_Objects() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (PrintWriter out = newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.format(Locale.getDefault(), "", "");
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void format_Locale_Objects() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (PrintWriter out = newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.format(Locale.getDefault(), "");
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void print_b() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (PrintWriter out = newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.print(true);
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void print_i() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (PrintWriter out = newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.print(3);
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void printf_Locale_String_Object() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (PrintWriter out = newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.printf(Locale.getDefault(), "", 3);
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void printf_Locale_Object() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (PrintWriter out = newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.printf(Locale.getDefault(), "");
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void println_b() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (PrintWriter out = newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.println(true);
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void println_i() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (PrintWriter out = newWriter())
		{
			ConfigAuditReactive.strict.commit();
			out.println(3);
		}
	}

	@Test
	public void derived() throws IOException
	{
		class Derived extends PrintWriter
		{
			Derived() throws IOException
			{
				super(new ByteArrayOutputStream(10));
			}
		}
		;
		new Derived();
	}
}
