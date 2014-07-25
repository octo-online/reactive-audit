package com.octo.reactive.audit.java.rmi.server;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.server.RMIServerSocketFactory;

/**
 * Created by pprados on 19/05/2014.
 */
public class RMIServerSocketFactoryTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void createServerSocket() throws Exception
	{
		AuditReactive.strict.commit();
		RMIServerSocketFactory factory = new RMIServerSocketFactory()
		{
			@Override
			public ServerSocket createServerSocket(int port) throws IOException
			{
				return null;
			}
		};
		factory.createServerSocket(1);
	}

}
