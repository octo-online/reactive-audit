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
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import static com.octo.reactive.audit.IOTestTools.HOST;
import static java.net.InetAddress.getByName;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class SocketTest
{

	private static final int PORT = 80;

	@Test
	public void new_1()
			throws IOException
	{
		ReactiveAudit.strict.commit();
		try (Socket socket = new Socket())
		{
			socket.isConnected();
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void new_2()
			throws IOException
	{
		ReactiveAudit.off.commit();
		InetAddress target = getByName(HOST);
		ReactiveAudit.strict.commit();
		try (Socket socket = new Socket(target, PORT))
		{
			socket.isConnected();
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	@SuppressWarnings("deprecation")
	public void new_3()
			throws IOException
	{
		ReactiveAudit.off.commit();
		InetAddress target = getByName(HOST);
		ReactiveAudit.strict.commit();
		try (Socket socket = new Socket(target, PORT, false))
		{
			socket.isConnected();
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void new_4()
			throws IOException
	{
		ReactiveAudit.off.commit();
		InetAddress target = getByName(HOST);
		ReactiveAudit.strict.commit();
		try (Socket socket = new Socket(target, PORT, null, 0))
		{
			socket.isConnected();
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void new_5()
			throws IOException
	{
		ReactiveAudit.off.commit();
		ReactiveAudit.strict.commit();
		try (Socket socket = new Socket(HOST, PORT))
		{
			socket.isConnected();
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void new_6()
			throws IOException
	{
		ReactiveAudit.off.commit();
		ReactiveAudit.strict.commit();
		try (Socket socket = new Socket(HOST, PORT))
		{
			socket.isConnected();
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void new_7()
			throws IOException
	{
		ReactiveAudit.off.commit();
		ReactiveAudit.strict.commit();
		try (Socket socket = new Socket(HOST, PORT, null, 0))
		{
			socket.isConnected();
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void connect()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Socket socket = new Socket())
		{
			SocketAddress add = new InetSocketAddress(HOST, PORT);
			ReactiveAudit.strict.commit();
			socket.connect(add);
		}
	}

	@Test
	public void getInputStream()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Socket socket = new Socket(HOST, PORT))
		{
			ReactiveAudit.strict.commit();
			socket.getInputStream();
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getInputStream_use()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Socket socket = new Socket(HOST, PORT))
		{
			ReactiveAudit.strict.commit();
			socket.getInputStream().read();
		}
	}

	@Test
	public void getOutputStream()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Socket socket = new Socket(HOST, PORT))
		{
			ReactiveAudit.strict.commit();
			socket.getOutputStream();
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getOutputStream_use()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (Socket socket = new Socket(HOST, PORT))
		{
			ReactiveAudit.strict.commit();
			socket.getOutputStream().write(new byte[1]);
		}
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void sendUrgentData()
			throws IOException
	{
		final int DATA = 255;
		ReactiveAudit.off.commit();
		try (Socket socket = new Socket("www.google.com", PORT))
		{
			ReactiveAudit.strict.commit();
			socket.sendUrgentData(DATA);
		}
	}
}
