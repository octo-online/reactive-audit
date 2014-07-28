package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.octo.reactive.audit.IOTestTools.HOST;
import static com.octo.reactive.audit.IOTestTools.PORT;

public class HttpURLConnectionTest extends URLConnectionTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void getResponseCode() throws IOException
	{
		AuditReactive.off.commit();
		HttpURLConnection conn = (HttpURLConnection) new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getResponseCode();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getResponseMessage() throws IOException
	{
		AuditReactive.off.commit();
		HttpURLConnection conn = (HttpURLConnection) new URL("http://" + HOST + ":" + PORT).openConnection();
		AuditReactive.strict.commit();
		conn.getResponseMessage();
	}
}
