package com.octo.reactive.audit.javax.net.ssl;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;

public class SSLSocketTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void New()
	{
		AuditReactive.strict.commit();
		new MySSLSocket();
	}

	private class MySSLSocket extends SSLSocket
	{
		@Override
		public String[] getSupportedCipherSuites()
		{
			return new String[0];
		}

		@Override
		public String[] getEnabledCipherSuites()
		{
			return new String[0];
		}

		@Override
		public void setEnabledCipherSuites(String[] strings)
		{

		}

		@Override
		public String[] getSupportedProtocols()
		{
			return new String[0];
		}

		@Override
		public String[] getEnabledProtocols()
		{
			return new String[0];
		}

		@Override
		public void setEnabledProtocols(String[] strings)
		{

		}

		@Override
		public SSLSession getSession()
		{
			return null;
		}

		@Override
		public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener)
		{

		}

		@Override
		public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener)
		{

		}

		@Override
		public void startHandshake() throws IOException
		{

		}

		@Override
		public boolean getUseClientMode()
		{
			return false;
		}

		@Override
		public void setUseClientMode(boolean b)
		{

		}

		@Override
		public boolean getNeedClientAuth()
		{
			return false;
		}

		@Override
		public void setNeedClientAuth(boolean b)
		{

		}

		@Override
		public boolean getWantClientAuth()
		{
			return false;
		}

		@Override
		public void setWantClientAuth(boolean b)
		{

		}

		@Override
		public boolean getEnableSessionCreation()
		{
			return false;
		}

		@Override
		public void setEnableSessionCreation(boolean b)
		{

		}
	}

}
