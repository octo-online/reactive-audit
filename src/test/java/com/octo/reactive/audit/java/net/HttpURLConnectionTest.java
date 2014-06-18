package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pprados on 06/05/14.
 */
public class HttpURLConnectionTest extends URLConnectionTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void getResponseCode() throws IOException
	{
		ConfigAuditReactive.off.commit();
		HttpURLConnection conn = (HttpURLConnection) new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getResponseCode();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getResponseMessage() throws IOException
	{
		ConfigAuditReactive.off.commit();
		HttpURLConnection conn = (HttpURLConnection) new URL("http://" + host + ":" + PORT).openConnection();
		ConfigAuditReactive.strict.commit();
		conn.getResponseMessage();
	}
}
