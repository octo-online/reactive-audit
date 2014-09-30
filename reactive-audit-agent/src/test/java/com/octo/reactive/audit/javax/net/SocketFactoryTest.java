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

package com.octo.reactive.audit.javax.net;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketFactoryTest
{
	private final SocketFactory sf = new SocketFactory()
	{

		@Override
		public Socket createSocket(String s, int i)
		{
			return null;
		}

		@Override
		public Socket createSocket(String s, int i, InetAddress inetAddress, int i2)
		{
			return null;
		}

		@Override
		public Socket createSocket(InetAddress inetAddress, int i)
				throws IOException
		{
			return null;
		}

		@Override
		public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2)
				throws IOException
		{
			return null;
		}
	};

	@Test(expected = NetworkReactiveAuditException.class)
	public void createSocket()
			throws IOException
	{
		TestTools.strict.commit();
		sf.createSocket();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void createSocket_InetAddressI()
			throws IOException
	{
		TestTools.strict.commit();
		sf.createSocket(InetAddress.getLocalHost(), 1);
	}
}
