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

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import static com.octo.reactive.audit.IOTestTools.HOST;
import static com.octo.reactive.audit.IOTestTools.PORT;

public class SocketChannelTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void connect()
			throws IOException
	{
		try (SocketChannel r = IOTestTools.getSocketChannel())
		{
			AuditReactive.strict.commit();
			r.connect(null);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void finishConnect()
			throws IOException
	{
		try (SocketChannel r = IOTestTools.getSocketChannel())
		{
			AuditReactive.strict.commit();
			r.finishConnect();
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void open()
			throws IOException
	{
		AuditReactive.strict.commit();
		SocketChannel.open(new InetSocketAddress(HOST, PORT));
	}
}
