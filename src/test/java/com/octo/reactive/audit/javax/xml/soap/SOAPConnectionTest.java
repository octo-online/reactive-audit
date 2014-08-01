package com.octo.reactive.audit.javax.xml.soap;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SOAPConnectionTest
{
	private final SOAPConnection x = new SOAPConnection()
	{

		@Override
		public SOAPMessage call(SOAPMessage request, Object to) throws SOAPException
		{
			return null;
		}

		@Override
		public void close() throws SOAPException
		{

		}
	};

	@Test(expected = NetworkAuditReactiveException.class)
	public void call() throws SOAPException
	{
		AuditReactive.strict.commit();
		x.call(null, null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void get() throws SOAPException
	{
		AuditReactive.strict.commit();
		x.get(null);
	}

}
