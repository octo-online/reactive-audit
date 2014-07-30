package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import java.io.IOException;

public class JAXBTest
{
	XAResource x = (XAResource) TestTools.createProxy(XAResource.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void commit() throws InterruptedException, IOException, XAException
	{
		AuditReactive.strict.commit();
		x.commit(null, true);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void rollback() throws InterruptedException, IOException, XAException
	{
		AuditReactive.strict.commit();
		x.rollback(null);
	}

}
