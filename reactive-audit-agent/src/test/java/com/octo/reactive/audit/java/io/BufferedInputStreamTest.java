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

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class BufferedInputStreamTest extends FileInputStreamTest
{
	@Override
	protected InputStream newInputStream()
			throws IOException
	{
		push();
		InputStream out = super.newInputStream();
		pop();
		return new BufferedInputStream(out);
	}

	@Override
	@Test
	public void New()
			throws IOException
	{
		super.New();
	}

	@Test
	public void derived()
	{
		class Derived extends BufferedInputStream
		{
			public Derived()
			{
				super(new ByteArrayInputStream(new byte[10]));
			}
		}
		new Derived();
	}
}
