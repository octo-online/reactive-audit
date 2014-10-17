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
import org.junit.Assume;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.function.Supplier;

import static com.octo.reactive.audit.IOTestTools.HOST;
import static com.octo.reactive.audit.IOTestTools.PORT;

public class DatagramChannelTest
{
	// Java8 private final Supplier<DatagramChannel> channel = IOTestTools::getDatagramChannel;
	private final Supplier<DatagramChannel> channel =
			new Supplier<DatagramChannel>()
			{
				@Override
				public DatagramChannel get()
				{
					return IOTestTools.getDatagramChannel();
				}

			};

	@Test(expected = NetworkReactiveAuditException.class)
	public void connect()
			throws IOException
	{
		try (DatagramChannel w = channel.get())
		{
			TestTools.strict.commit();
			w.connect(null);
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void receive_b()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			TestTools.strict.commit();
			w.receive(buf);
		}
	}

	@Test
	public void receive_nb()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			w.configureBlocking(false);
			TestTools.strict.commit();
			w.receive(buf);
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void send_b()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			TestTools.strict.commit();
			w.send(buf, new InetSocketAddress(HOST, PORT));
		}
	}

	@Test
	public void send_nb()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			w.configureBlocking(false);
			TestTools.strict.commit();
			w.send(buf, new InetSocketAddress(HOST, PORT));
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void read_b()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			TestTools.strict.commit();
			w.read(buf);
		}
	}

	@Test(expected = java.nio.channels.NotYetConnectedException.class)
	public void read_nb()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			w.configureBlocking(false);
			TestTools.strict.commit();
			w.read(buf);
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void write_b()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			TestTools.strict.commit();
			w.read(buf);
		}
	}

	@Test(expected = java.nio.channels.NotYetConnectedException.class)
	public void write_nb()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			w.configureBlocking(false);
			TestTools.strict.commit();
			w.read(buf);
		}
	}
}
