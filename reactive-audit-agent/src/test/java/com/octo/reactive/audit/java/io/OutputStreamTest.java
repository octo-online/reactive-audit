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
import java.io.OutputStream;

public abstract class OutputStreamTest
{
	protected abstract OutputStream newOutputStream()
			throws IOException;

	@Test
	public void New()
			throws IOException
	{
        TestTools.strict.commit();
		try (OutputStream in = newOutputStream())
		{
			ReactiveAudit.off.commit();
		}
	}

	@Test
	public void flush()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (OutputStream out = newOutputStream())
		{
            TestTools.strict.commit();
			out.flush();
		}
	}

	@Test
	public void close()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (OutputStream out = newOutputStream())
		{
            TestTools.strict.commit();
			out.close();
		}
	}

	@Test
	public void write_b()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (OutputStream out = newOutputStream())
		{
            TestTools.strict.commit();
			out.write((byte) 1);
		}
	}

	@Test
	public void write_B()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (OutputStream out = newOutputStream())
		{
            TestTools.strict.commit();
			out.write(new byte[1]);
		}
	}

	@Test
	public void write_Bii()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (OutputStream out = newOutputStream())
		{
            TestTools.strict.commit();
			out.write(new byte[1], 0, 1);
		}
	}
}
