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

package com.octo.reactive.audit.java.rmi.server;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

public class RMISocketFactoryTest
{
	@Test(expected = NetworkReactiveAuditException.class)
	public void createServerSocket()
			throws Exception
	{
        TestTools.strict.commit();
		RMISocketFactory factory = getRmiSocketFactory();
		factory.createServerSocket(1);
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void createSocket()
			throws Exception
	{
        TestTools.strict.commit();
		RMISocketFactory factory = getRmiSocketFactory();
		factory.createSocket("", 1);
	}

	private RMISocketFactory getRmiSocketFactory()
	{
		return new RMISocketFactory()
		{
			@Override
			public Socket createSocket(String host, int port)
					throws IOException
			{
				return null;
			}

			@Override
			public ServerSocket createServerSocket(int port)
					throws IOException
			{
				return null;
			}
		};
	}

}
