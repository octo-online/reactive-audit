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

public abstract class AuditOutputStreamTest extends OutputStreamTest
{
	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void flush()
			throws IOException
	{
		super.flush();
	}

	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void close()
			throws IOException
	{
		super.close();
	}

	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void write_b()
			throws IOException
	{
		super.write_b();
	}

	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void write_B()
			throws IOException
	{
		super.write_B();
	}

	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void write_Bii()
			throws IOException
	{
		super.write_Bii();
	}
}
