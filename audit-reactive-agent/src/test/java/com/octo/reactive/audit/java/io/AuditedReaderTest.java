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

import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.IOException;

public abstract class AuditedReaderTest extends ReaderTest
{
	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void close()
			throws IOException
	{
		super.close();
	}

	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void read()
			throws IOException
	{
		super.read();
	}

	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void read_C()
			throws IOException
	{
		super.read_C();
	}

	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void read_Cii()
			throws IOException
	{
		super.read_Cii();
	}

	//	@Override  TODO CharBuffer
	//	@Test(expected=AuditReactiveException.class)
//	public void read_CharBuffer() throws IOException
//	{
//		super.read_CharBuffer();
//	}
	@Override
	@Test(expected = FileAuditReactiveException.class)
	public void skip()
			throws IOException
	{
		super.skip();
	}
}
