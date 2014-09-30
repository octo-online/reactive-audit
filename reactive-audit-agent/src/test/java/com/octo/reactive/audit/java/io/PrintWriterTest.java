/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import java.io.*;
import java.util.Locale;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class PrintWriterTest extends OutputStreamWriterTest
{
	@SuppressWarnings("RefusedBequest")
	@Override
	protected PrintWriter newWriter()
			throws IOException
	{
		push();
		FileOutputStream out = new FileOutputStream(IOTestTools.getTempFile());
		Writer writer = new OutputStreamWriter(out);
		pop();
		return new PrintWriter(writer);
	}

	@SuppressWarnings("RedundantMethodOverride")
	@Test
	@Override
	public void derived()
			throws IOException
	{
		class Derived extends PrintWriter
		{
			Derived()
			{
				super(new ByteArrayOutputStream(10));
			}
		}
		new Derived();
	}

	@SuppressWarnings("MalformedFormatString")
	@Test(expected = FileReactiveAuditException.class)
	public void format_Locale_String_Objects()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (PrintWriter out = newWriter())
		{
            TestTools.strict.commit();
			out.format(Locale.getDefault(), "", "");
		}
	}

	@Test(expected = FileReactiveAuditException.class)
	public void format_Locale_Objects()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (PrintWriter out = newWriter())
		{
            TestTools.strict.commit();
			out.format(Locale.getDefault(), "");
		}
	}

	@Test(expected = FileReactiveAuditException.class)
	public void print_b()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (PrintWriter out = newWriter())
		{
            TestTools.strict.commit();
			out.print(true);
		}
	}

	@Test(expected = FileReactiveAuditException.class)
	public void print_i()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (PrintWriter out = newWriter())
		{
            TestTools.strict.commit();
			out.print(3);
		}
	}

	@SuppressWarnings("MalformedFormatString")
	@Test(expected = FileReactiveAuditException.class)
	public void printf_Locale_String_Object()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (PrintWriter out = newWriter())
		{
            TestTools.strict.commit();
			out.printf(Locale.getDefault(), "", 3);
		}
	}

	@Test(expected = FileReactiveAuditException.class)
	public void printf_Locale_Object()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (PrintWriter out = newWriter())
		{
            TestTools.strict.commit();
			out.printf(Locale.getDefault(), "");
		}
	}

	@Test(expected = FileReactiveAuditException.class)
	public void println_b()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (PrintWriter out = newWriter())
		{
            TestTools.strict.commit();
			out.println(true);
		}
	}

	@Test(expected = FileReactiveAuditException.class)
	public void println_i()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (PrintWriter out = newWriter())
		{
            TestTools.strict.commit();
			out.println(3);
		}
	}
}
