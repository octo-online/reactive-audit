package com.octo.reactive.audit.java.rmi.server;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.RMISocketFactory;

/**
 * Created by pprados on 19/05/2014.
 */
public class RMISocketFactoryTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void createServerSocket() throws Exception
	{
		AuditReactive.strict.commit();
		RMISocketFactory factory = getRmiSocketFactory();
		factory.createServerSocket(1);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void createSocket() throws Exception
	{
		AuditReactive.strict.commit();
		RMISocketFactory factory = getRmiSocketFactory();
		factory.createSocket("", 1);
	}

	private RMISocketFactory getRmiSocketFactory()
	{
		return new RMISocketFactory()
		{
			@Override
			public Socket createSocket(String host, int port) throws IOException
			{
				return null;
			}

			@Override
			public ServerSocket createServerSocket(int port) throws IOException
			{
				return null;
			}
		};
	}

}
