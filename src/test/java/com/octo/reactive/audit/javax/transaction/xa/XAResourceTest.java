package com.octo.reactive.audit.javax.transaction.xa;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;

public class XAResourceTest
{
	private final XAResource x = (XAResource) TestTools.createProxy(XAResource.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void commit() throws XAException
	{
		AuditReactive.strict.commit();
		x.commit(null, true);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void rollback() throws XAException
	{
		AuditReactive.strict.commit();
		x.rollback(null);
	}

}
