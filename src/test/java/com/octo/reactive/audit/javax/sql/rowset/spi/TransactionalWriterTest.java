package com.octo.reactive.audit.javax.sql.rowset.spi;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.rmi.PortableRemoteObject;
import javax.sql.rowset.spi.TransactionalWriter;
import java.io.IOException;

public class TransactionalWriterTest
{
	TransactionalWriter tw = (TransactionalWriter)TestTools.createProxy(TransactionalWriter.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws InterruptedException, IOException
	{
		AuditReactive.strict.commit();
		PortableRemoteObject.connect(null, null);
	}
}
