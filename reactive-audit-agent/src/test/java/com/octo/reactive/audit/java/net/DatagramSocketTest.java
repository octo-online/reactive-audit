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

package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class DatagramSocketTest
{

	private static final int PORT = 8080;

	@Test(expected = NetworkReactiveAuditException.class)
	public void connect()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (DatagramSocket socket = new DatagramSocket())
		{
			InetSocketAddress localhost = new InetSocketAddress("localhost", PORT);
			TestTools.strict.commit();
			socket.connect(localhost);
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void receive()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (DatagramSocket socket = new DatagramSocket())
		{
			new InetSocketAddress("localhost", PORT);
			TestTools.strict.commit();
			DatagramPacket packet = new DatagramPacket(new byte[10], 10);
			socket.receive(packet);
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void send()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (DatagramSocket socket = new DatagramSocket())
		{
			new InetSocketAddress("localhost", PORT);
			TestTools.strict.commit();
			DatagramPacket packet = new DatagramPacket(new byte[10], 10);
			socket.send(packet);
		}
	}
}
