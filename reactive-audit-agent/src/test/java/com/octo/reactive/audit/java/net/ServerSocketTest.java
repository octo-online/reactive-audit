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
import java.net.ServerSocket;

public class ServerSocketTest
{
	private static final int PORT = 12345;

	@Test(expected = NetworkReactiveAuditException.class)
	public void accept()
			throws IOException
	{
		ReactiveAudit.off.commit();
		try (ServerSocket socket = new ServerSocket(PORT))
		{
            TestTools.strict.commit();
			ReactiveAudit.config.begin().debug(true).commit();
			socket.accept();
		}
	}
}
