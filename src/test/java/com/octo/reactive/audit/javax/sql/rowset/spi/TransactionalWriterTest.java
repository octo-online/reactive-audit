package com.octo.reactive.audit.javax.sql.rowset.spi;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.rowset.spi.TransactionalWriter;
import java.sql.SQLException;

public class TransactionalWriterTest
{
	private final TransactionalWriter tw = (TransactionalWriter) TestTools.createProxy(TransactionalWriter.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void commit() throws SQLException
	{
		AuditReactive.strict.commit();
		tw.commit();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void rollback() throws SQLException
	{
		AuditReactive.strict.commit();
		tw.rollback();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void rollback_SavePoint() throws SQLException
	{
		AuditReactive.strict.commit();
		tw.rollback(null);
	}
}
