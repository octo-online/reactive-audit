package com.octo.reactive.audit.javax.rmi.ssl;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.rmi.PortableRemoteObject;
import java.io.IOException;

public class SslRMIClientSocketFactoryTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws InterruptedException, IOException
	{
		AuditReactive.strict.commit();
		PortableRemoteObject.connect(null, null);
	}
}
