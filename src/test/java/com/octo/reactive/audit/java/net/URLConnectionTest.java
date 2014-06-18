package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by pprados on 06/05/14.
 */
public abstract class URLConnectionTest
{
	// TODO: start et stop un pseudo serveur HTTP
	protected static final String host = "www.google.com";
	protected static final int    PORT = 80;

	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws IOException
	{
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		conn.connect();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContent() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getContent();
	}

	@Test
	public void getContent_2() throws IOException
	{
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.off.commit();
		conn.connect();
		ConfigAuditReactive.strict.commit();
		conn.getContent();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContent_AClass() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getContent(new Class[]{String.class});
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContentEncoding() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getContentEncoding();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContentLength() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getContentLength();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContentLengthLong() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getContentLengthLong();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getContentType() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getContentType();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getDate() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getDate();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getExpiration() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getExpiration();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderField_i() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getHeaderField(0);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderField() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getHeaderField("mime/type");
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderFieldDate() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getHeaderFieldDate("expiration", 0);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderFieldInt() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getHeaderFieldInt("expiration", 0);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderFieldKey() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getHeaderFieldKey(0);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderFieldLong() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getHeaderFieldLong("expiration", 0);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHeaderFields() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getHeaderFields();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getIfModifiedSince() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getIfModifiedSince();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getInputStream() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getInputStream();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getInputStream_use() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		InputStream in = conn.getInputStream();
		ConfigAuditReactive.strict.commit();
		in.read();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getLastModified() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getLastModified();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getOutputStream() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		conn.setDoOutput(true);
		ConfigAuditReactive.strict.commit();
		conn.getOutputStream();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getOutputStream_use() throws IOException
	{
		ConfigAuditReactive.off.commit();
		URLConnection conn = new URL("http://" + host + ":" + PORT).openConnection();
		conn.setDoOutput(true);
		OutputStream out = conn.getOutputStream();
		ConfigAuditReactive.strict.commit();
		out.write(new byte[1]);
	}

	@Test
	public void bind()
	{

	}
}
