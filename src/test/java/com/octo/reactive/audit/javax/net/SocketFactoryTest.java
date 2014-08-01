package com.octo.reactive.audit.javax.net;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketFactoryTest
{
	private final SocketFactory sf = new SocketFactory()
	{

		@Override
		public Socket createSocket(String s, int i)
		{
			return null;
		}

		@Override
		public Socket createSocket(String s, int i, InetAddress inetAddress, int i2)
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
	public void createSocket() throws IOException
	{
		AuditReactive.strict.commit();
		sf.createSocket();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void createSocket_InetAddressI() throws IOException
	{
		AuditReactive.strict.commit();
		sf.createSocket(InetAddress.getLocalHost(), 1);
	}
}
