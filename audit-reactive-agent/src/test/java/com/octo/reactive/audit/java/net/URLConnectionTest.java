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

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
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
	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws IOException
	{
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		conn.connect();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContent() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getContent();
	}

	@Test
	public void getContent_2() throws IOException
	{
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.off.commit();
		conn.connect();
		AuditReactive.strict.commit();
		conn.getContent();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContent_AClass() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getContent(new Class[]{String.class});
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContentEncoding() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getContentEncoding();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContentLength() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getContentLength();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContentLengthLong() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getContentLengthLong();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContentType() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getContentType();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getDate() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getDate();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getExpiration() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getExpiration();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderField_i() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getHeaderField(0);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderField() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getHeaderField("mime/type");
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderFieldDate() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getHeaderFieldDate("expiration", 0);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderFieldInt() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getHeaderFieldInt("expiration", 0);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderFieldKey() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getHeaderFieldKey(0);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderFieldLong() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getHeaderFieldLong("expiration", 0);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderFields() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getHeaderFields();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getIfModifiedSince() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getIfModifiedSince();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getInputStream() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getInputStream();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getInputStream_use() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		InputStream in = conn.getInputStream();
		AuditReactive.strict.commit();
		in.read();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getLastModified() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getLastModified();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getOutputStream() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		conn.setDoOutput(true);
		AuditReactive.strict.commit();
		conn.getOutputStream();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getOutputStream_use() throws IOException
	{
		AuditReactive.off.commit();
		URLConnection conn = new URL("http://" + HOST + ":" + PORT).openConnection();
		conn.setDoOutput(true);
		OutputStream out = conn.getOutputStream();
		AuditReactive.strict.commit();
		out.write(new byte[1]);
	}

	@Test
	public void bind()
	{

	}
}
