package com.octo.reactive.audit.javax.xml.soap;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;

public class SOAPConnectionFactoryTest
{
	private final SOAPConnectionFactory x = new SOAPConnectionFactory()
	{

		@Override
		public SOAPConnection createConnection() throws SOAPException
		{
			return null;
		}
	};

	@Test(expected = NetworkAuditReactiveException.class)
	public void createConnection() throws SOAPException
	{
		AuditReactive.strict.commit();
		x.createConnection();
	}

}
