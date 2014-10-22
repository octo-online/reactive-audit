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
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public abstract class AuditedReaderTest extends ReaderTest
{
	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void close()
			throws IOException
	{
		super.close();
	}

	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void read()
			throws IOException
	{
		super.read();
	}

	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void read_C()
			throws IOException
	{
		super.read_C();
	}

	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void read_Cii()
			throws IOException
	{
		super.read_Cii();
	}

	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void read_CharBuffer() throws IOException
	{
		CharBuffer cb = CharBuffer.allocate(12);
		ReactiveAudit.off.commit();
		try (Reader in= newReader())
		{
			TestTools.strict.commit();
			in.read(cb);
		}
	}

	@Override
	@Test(expected = FileReactiveAuditException.class)
	public void skip()
			throws IOException
	{
		super.skip();
	}
}
