package com.octo.reactive.audit.javax.net.ssl;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SSLSocketTest
{
	SocketFactory sf=new SocketFactory()
	{

		@Override
		public Socket createSocket(String s, int i) throws IOException, UnknownHostException
		{
			return null;
		}

		@Override
		public Socket createSocket(String s, int i, InetAddress inetAddress, int i2)
		throws IOException, UnknownHostException
		{
			return null;
		}

		@Override
		public Socket createSocket(InetAddress inetAddress, int i) throws IOException
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
	@Test(expected = NetworkAuditReactiveException.class)
	public void createSocket() throws InterruptedException, IOException
	{
		AuditReactive.strict.commit();
		sf.createSocket();
	}
	@Test(expected = NetworkAuditReactiveException.class)
	public void createSocket_InetAddressI() throws InterruptedException, IOException
	{
		AuditReactive.strict.commit();
		sf.createSocket(InetAddress.getLocalHost(), 1);
	}
}
