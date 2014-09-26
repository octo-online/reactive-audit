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
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public abstract class ReaderTest
{
	protected abstract Reader newReader()
			throws IOException;

	@Test
	public void New()
			throws IOException
	{
		ReactiveAudit.strict.commit();
		try (Reader reader = newReader())
		{
			ReactiveAudit.off.commit();
		}

	}

	@Test
	public void close()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Reader in = newReader())
		{
			ReactiveAudit.strict.commit();
			in.close();
		}
	}

	@Test
	public void read()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Reader in = newReader())
		{
			ReactiveAudit.strict.commit();
			in.read();
		}
	}

	@Test
	public void read_C()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Reader in = newReader())
		{
			ReactiveAudit.strict.commit();
			in.read(new char[1]);
		}
	}

	@Test
	public void read_Cii()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Reader in = newReader())
		{
			ReactiveAudit.strict.commit();
			in.read(new char[1], 0, 1);
		}
	}

	//	@Test TODO CharBuffer
//	public void read_CharBuffer() throws IOException
//	{
//		CharBuffer buffer=new CharBuffer();
//		ConfigReactiveAudit.off.commit();
//		try (Reader in= newReader())
//		{
//			ConfigReactiveAudit.strict.commit();
//			in.read(new byte[1], 0, 1);
//		}
//	}
	@Test
	public void skip()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Reader in = newReader())
		{
			ReactiveAudit.strict.commit();
			in.skip(0);
		}
	}

}
