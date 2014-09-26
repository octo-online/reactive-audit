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
import java.io.SequenceInputStream;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class SequenceInputStreamTest extends ByteArrayInputStreamTest
{
	@Override
	protected InputStream newInputStream()
			throws IOException
	{
		push();
		InputStream in1 = super.newInputStream();
		InputStream in2 = super.newInputStream();
		pop();
		return new SequenceInputStream(in1, in2);
	}

	@SuppressWarnings("RedundantMethodOverride")
	@Test
	@Override
	public void derived()
			throws IOException
	{
		class Derived extends SequenceInputStream
		{
			Derived()
			{
				super(new ByteArrayInputStream(new byte[10]), new ByteArrayInputStream(new byte[10]));
			}
		}
		new Derived();
	}
}
