package com.octo.reactive.audit.javax.rmi.ssl;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.io.IOException;

public class SslRMIClientSocketFactoryTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws IOException
	{
		AuditReactive.strict.commit();
		new SslRMIClientSocketFactory().createSocket(null, 0);
	}
}
