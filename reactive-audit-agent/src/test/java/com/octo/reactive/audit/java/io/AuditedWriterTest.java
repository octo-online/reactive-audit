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

import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.io.Writer;

public abstract class AuditedWriterTest extends WriterTest
{
	@Override
	protected abstract Writer newWriter()
			throws IOException;

	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void append_c()
			throws IOException
	{
		super.append_c();
	}

	@Test(expected = FileReactiveAuditException.class)
	@Override
	public void append_CharSequence()
			throws IOException
	{
		super.append_CharSequence();
	}

	@Test(expected = FileReactiveAuditException.class)
	@Override
	public void append_CharSequence_ii()
			throws IOException
	{
		super.append_CharSequence_ii();
	}

	@Test(expected = FileReactiveAuditException.class)
	@Override
	public void close()
			throws IOException
	{
		super.close();
	}

	@Test(expected = FileReactiveAuditException.class)
	@Override
	public void flush()
			throws IOException
	{
		super.flush();
	}

	@Test(expected = FileReactiveAuditException.class)
	@Override
	public void write_C()
			throws IOException
	{
		super.write_C();
	}

	@Test(expected = FileReactiveAuditException.class)
	@Override
	public void write_Cii()
			throws IOException
	{
		super.write_Cii();
	}

	@Test(expected = FileReactiveAuditException.class)
	@Override
	public void write_i()
			throws IOException
	{
		super.write_i();
	}

	@Test(expected = FileReactiveAuditException.class)
	@Override
	public void write_String()
			throws IOException
	{
		super.write_String();
	}

	@Test(expected = FileReactiveAuditException.class)
	@Override
	public void write_String_ii()
			throws IOException
	{
		super.write_String_ii();
	}
}
