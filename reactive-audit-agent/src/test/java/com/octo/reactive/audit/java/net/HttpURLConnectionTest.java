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

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Assume;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.octo.reactive.audit.IOTestTools.HOST;
import static com.octo.reactive.audit.IOTestTools.PORT;

public class HttpURLConnectionTest extends URLConnectionTest
{
	@Test(expected = NetworkReactiveAuditException.class)
	public void getResponseCode()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ReactiveAudit.off.commit();
		HttpURLConnection conn = (HttpURLConnection) new URL("http://" + HOST + ":" + PORT).openConnection();
		TestTools.strict.commit();
		conn.getResponseCode();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getResponseMessage()
			throws IOException
	{
        Assume.assumeTrue(IOTestTools.isNetworkConnected());
		ReactiveAudit.off.commit();
		HttpURLConnection conn = (HttpURLConnection) new URL("http://" + HOST + ":" + PORT).openConnection();
		TestTools.strict.commit();
		conn.getResponseMessage();
	}
}
