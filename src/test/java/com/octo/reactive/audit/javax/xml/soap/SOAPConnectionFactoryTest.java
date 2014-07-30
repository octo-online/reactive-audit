package com.octo.reactive.audit.javax.xml.soap;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPException;
import java.io.IOException;

public class SOAPConnectionFactoryTest
{
	SOAPConnection x = (SOAPConnection) TestTools.createProxy(SOAPConnection.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void call() throws InterruptedException, IOException, SAXException, SOAPException
	{
		AuditReactive.strict.commit();
		x.call(null, null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void get() throws InterruptedException, IOException, SAXException, SOAPException
	{
		AuditReactive.strict.commit();
		x.get(null);
	}

}
