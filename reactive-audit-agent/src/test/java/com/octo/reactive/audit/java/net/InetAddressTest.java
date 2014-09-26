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

import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class InetAddressTest
{
	@Test(expected = NetworkReactiveAuditException.class)
	public void getAllByName()
			throws IOException
	{
		InetAddress.getAllByName("localhost");
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getByName()
			throws IOException
	{
		InetAddress.getByName("localhost");
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getHostName()
			throws IOException
	{
		InetAddress.getLocalHost().getHostName();
	}
}
