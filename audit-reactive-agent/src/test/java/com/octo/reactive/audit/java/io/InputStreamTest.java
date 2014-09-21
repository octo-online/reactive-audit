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

import com.octo.reactive.audit.AuditReactive;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public abstract class InputStreamTest
{
	protected abstract InputStream newInputStream() throws IOException;

	@Test
	public void New() throws IOException
	{
		AuditReactive.strict.commit();
		try (InputStream in = newInputStream())
		{
			AuditReactive.off.commit();
		}
	}

	@Test
	public void available() throws IOException
	{
		AuditReactive.off.commit();
		try (InputStream in = newInputStream())
		{
			AuditReactive.strict.commit();
			in.available();
		}
	}

	@Test
	public void close() throws IOException
	{
		AuditReactive.off.commit();
		try (InputStream in = newInputStream())
		{
			AuditReactive.strict.commit();
			in.close();
		}
	}

	@Test
	public void read() throws IOException
	{
		AuditReactive.off.commit();
		try (InputStream in = newInputStream())
		{
			AuditReactive.strict.commit();
			in.read();
		}
	}

	@Test
	public void read_B() throws IOException
	{
		AuditReactive.off.commit();
		try (InputStream in = newInputStream())
		{
			AuditReactive.strict.commit();
			in.read(new byte[1]);
		}
	}

	@Test
	public void read_Bii() throws IOException
	{
		AuditReactive.off.commit();
		try (InputStream in = newInputStream())
		{
			AuditReactive.strict.commit();
			in.read(new byte[1], 0, 1);
		}
	}

	@Test
	public void skip() throws IOException
	{
		AuditReactive.off.commit();
		try (InputStream in = newInputStream())
		{
			AuditReactive.strict.commit();
			in.skip(0);
		}
	}
}
