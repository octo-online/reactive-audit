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
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.util.function.Supplier;

public class GatheringByteChannelTest
{
	//Java8 private final Supplier<GatheringByteChannel> channel = IOTestTools::getOutputFileChannel;
	private final Supplier<GatheringByteChannel> channel =
			new Supplier<GatheringByteChannel>()
			{
				@Override
				public GatheringByteChannel get()
				{
					return IOTestTools.getOutputFileChannel();
				}
			};

	@Test(expected = FileReactiveAuditException.class)
	public void write()
			throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (GatheringByteChannel w = channel.get())
		{
            TestTools.strict.commit();
			ByteBuffer[] srcs = {buf};
			w.write(srcs);
		}
	}
}
