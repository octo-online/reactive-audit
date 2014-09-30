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

import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.TestTools;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;

public abstract class WriterTest
{
	protected abstract Writer newWriter()
			throws IOException;

	@Test
	public void New()
			throws IOException
	{
        TestTools.strict.commit();
		try (Writer writer = newWriter())
		{
			ReactiveAudit.off.commit();
		}

	}

	@Test
	public void append_c()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Writer out = newWriter())
		{
            TestTools.strict.commit();
			out.append('c');
		}
	}

	@Test
	public void append_CharSequence()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Writer out = newWriter())
		{
            TestTools.strict.commit();
			out.append("abc");
		}
	}

	@Test
	public void append_CharSequence_ii()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Writer out = newWriter())
		{
            TestTools.strict.commit();
			out.append("abc", 0, 1);
		}
	}

	@Test
	public void close()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Writer out = newWriter())
		{
            TestTools.strict.commit();
			out.close();
		}
	}

	@Test
	public void flush()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Writer out = newWriter())
		{
            TestTools.strict.commit();
			out.flush();
		}
	}

	@Test
	public void write_C()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Writer out = newWriter())
		{
            TestTools.strict.commit();
			out.write(new char[1]);
		}
	}

	@Test
	public void write_Cii()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Writer out = newWriter())
		{
            TestTools.strict.commit();
			out.write(new char[1], 0, 1);
		}
	}

	@Test
	public void write_i()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Writer out = newWriter())
		{
            TestTools.strict.commit();
			out.write(0);
		}
	}

	@Test
	public void write_String()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Writer out = newWriter())
		{
            TestTools.strict.commit();
			out.write("abc");
		}
	}

	@Test
	public void write_String_ii()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Writer out = newWriter())
		{
            TestTools.strict.commit();
			out.write("abc", 0, 1);
		}
	}
}
