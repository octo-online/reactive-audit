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

package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ScatteringByteChannel;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

@RunWith(Parameterized.class)
public class ScatteringByteChannelTest
{
	private final Supplier<ScatteringByteChannel> channel;

	public ScatteringByteChannelTest(Supplier<ScatteringByteChannel> channel)
	{
		this.channel = channel;
	}

	@Parameterized.Parameters
	public static Collection data()
			throws IOException
	{
		Supplier<?>[][] data = new Supplier<?>[][]
				{
						//Java8 {IOTestTools::getInputFileChannel},
						{
								new Supplier<Object>()
								{
									@Override
									public Object get()
									{
										return IOTestTools.getInputFileChannel();
									}
								}
						},
						//Java8 {IOTestTools::getSocketChannel},
						{
								new Supplier<Object>()
								{
									@Override
									public Object get()
									{
										return IOTestTools.getSocketChannel();
									}
								}
						},
				};
		return Arrays.asList(data);
	}

	@Test(expected = ReactiveAuditException.class)
	public void read()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (ScatteringByteChannel r = channel.get())
		{
            TestTools.strict.commit();
			ByteBuffer[] srcs = {buf};
			r.read(srcs);
		}
	}

	@Test(expected = ReactiveAuditException.class)
	public void read_ii()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (ScatteringByteChannel r = channel.get())
		{
            TestTools.strict.commit();
			ByteBuffer[] srcs = {buf};
			r.read(srcs, 0, 1);
		}
	}
}
