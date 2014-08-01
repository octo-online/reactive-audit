package com.octo.reactive.audit.javax.net.ssl;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SSLSocketFactoryTest
{
	private final SSLSocketFactory sf = new SSLSocketFactory()
	{
		@Override
		public String[] getDefaultCipherSuites()
		{
			return new String[0];
		}

		@Override
		public String[] getSupportedCipherSuites()
		{
			return new String[0];
		}

		@Override
		public Socket createSocket(Socket socket, String s, int i, boolean b) throws IOException
		{
			return null;
		}

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
		public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException
		{
			return null;
		}
	};


	@Test(expected = NetworkAuditReactiveException.class)
	public void createSocket() throws IOException
	{
		AuditReactive.strict.commit();
		sf.createSocket("localhost", 1);
	}

}
