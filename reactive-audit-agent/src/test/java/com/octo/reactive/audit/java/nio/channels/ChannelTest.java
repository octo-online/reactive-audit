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
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.Channel;
import java.util.function.Supplier;

public class ChannelTest
{
	// Java8 private final Supplier<Channel> channel = IOTestTools::getInputFileChannel;
	private final Supplier<Channel> channel = new Supplier<Channel>()
	{
		@Override
		public Channel get()
		{
			return IOTestTools.getInputFileChannel();
		}
	};

	@Test
	public void close_Interruptible()
			throws IOException
	{
		try (Channel w = channel.get())
		{
            TestTools.strict.commit();
			w.close();
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void close_uninterruptible()
			throws IOException
	{
		try (ByteChannel w = null)
		{

			TestTools.strict.commit();
			w.close();
		}
	}

}
