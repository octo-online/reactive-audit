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
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import static com.octo.reactive.audit.IOTestTools.HOST;
import static com.octo.reactive.audit.IOTestTools.PORT;

@SuppressWarnings("ResultOfMethodCallIgnored")
public abstract class URLConnectionTest
{
	@Test(expected = NetworkReactiveAuditException.class)
	public void connect()
			throws IOException
	{
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		conn.connect();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getContent()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getContent();
	}

	@Test
	public void getContent_2()
			throws IOException
	{
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.off.commit();
		conn.connect();
		ReactiveAudit.strict.commit();
		conn.getContent();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getContent_AClass()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getContent(new Class[]{String.class});
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getContentEncoding()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getContentEncoding();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getContentLength()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getContentLength();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getContentLengthLong()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getContentLengthLong();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getContentType()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getContentType();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getDate()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getDate();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getExpiration()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getExpiration();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getHeaderField_i()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getHeaderField(0);
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getHeaderField()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getHeaderField("mime/type");
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getHeaderFieldDate()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getHeaderFieldDate("expiration", 0);
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getHeaderFieldInt()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getHeaderFieldInt("expiration", 0);
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getHeaderFieldKey()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getHeaderFieldKey(0);
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getHeaderFieldLong()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getHeaderFieldLong("expiration", 0);
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getHeaderFields()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getHeaderFields();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getIfModifiedSince()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getIfModifiedSince();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getInputStream()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getInputStream();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getInputStream_use()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		InputStream in = conn.getInputStream();
		ReactiveAudit.strict.commit();
		in.read();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getLastModified()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		ReactiveAudit.strict.commit();
		conn.getLastModified();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getOutputStream()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		conn.setDoOutput(true);
		ReactiveAudit.strict.commit();
		conn.getOutputStream();
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void getOutputStream_use()
			throws IOException
	{
		ReactiveAudit.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		conn.setDoOutput(true);
		OutputStream out = conn.getOutputStream();
		ReactiveAudit.strict.commit();
		out.write(new byte[1]);
	}

	@Test
	public void bind()
	{

	}
}
