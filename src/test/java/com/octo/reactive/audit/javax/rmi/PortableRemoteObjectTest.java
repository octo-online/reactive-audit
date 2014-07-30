package com.octo.reactive.audit.javax.rmi;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class PortableRemoteObjectTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void createSocket() throws InterruptedException, IOException
	{
		AuditReactive.strict.commit();
		sf.createSocket();
	}
}
