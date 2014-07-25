package com.octo.reactive.audit.java.rmi.server;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;

/**
 * Created by pprados on 19/05/2014.
 */
public class RMIClientSocketFactoryTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void createSocket() throws Exception
	{
		AuditReactive.strict.commit();
		RMIClientSocketFactory factory = new RMIClientSocketFactory()
		{

			@Override
			public Socket createSocket(String host, int port) throws IOException
			{
				return null;
			}
		};
		factory.createSocket("", 1);
	}

}
