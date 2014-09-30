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
import java.io.InputStream;

@SuppressWarnings("ResultOfMethodCallIgnored")
public abstract class InputStreamTest
{
	protected abstract InputStream newInputStream()
			throws IOException;

	@Test
	public void New()
			throws IOException
	{
        TestTools.strict.commit();
		try (InputStream in = newInputStream())
		{
			ReactiveAudit.off.commit();
		}
	}

	@Test
	public void available()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (InputStream in = newInputStream())
		{
            TestTools.strict.commit();
			in.available();
		}
	}

	@Test
	public void close()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (InputStream in = newInputStream())
		{
            TestTools.strict.commit();
			in.close();
		}
	}

	@Test
	public void read()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (InputStream in = newInputStream())
		{
            TestTools.strict.commit();
			in.read();
		}
	}

	@Test
	public void read_B()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (InputStream in = newInputStream())
		{
            TestTools.strict.commit();
			in.read(new byte[1]);
		}
	}

	@Test
	public void read_Bii()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (InputStream in = newInputStream())
		{
            TestTools.strict.commit();
			in.read(new byte[1], 0, 1);
		}
	}

	@Test
	public void skip()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (InputStream in = newInputStream())
		{
            TestTools.strict.commit();
			in.skip(0);
		}
	}
}
