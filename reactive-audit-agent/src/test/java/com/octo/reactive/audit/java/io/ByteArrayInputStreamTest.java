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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteArrayInputStreamTest extends InputStreamTest
{
	@Override
	protected InputStream newInputStream()
			throws IOException
	{
		return new ByteArrayInputStream(new byte[10]);
	}

	@Test
	public void derived()
			throws IOException
	{
		class Derived extends ByteArrayInputStream
		{
			Derived()
			{
				super(new byte[10]);
			}
		}
		new Derived();
	}
}
