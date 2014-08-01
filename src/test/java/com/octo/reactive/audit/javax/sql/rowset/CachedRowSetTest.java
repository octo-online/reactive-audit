package com.octo.reactive.audit.javax.sql.rowset;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.spi.SyncProviderException;
import java.sql.SQLException;

public class CachedRowSetTest
{
	private final CachedRowSet x = (CachedRowSet) TestTools.createProxy(CachedRowSet.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void acceptChanges() throws SyncProviderException
	{
		AuditReactive.strict.commit();
		x.acceptChanges();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void acceptChanges_Connection() throws SyncProviderException
	{
		AuditReactive.strict.commit();
		x.acceptChanges(null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void commit() throws SQLException
	{
		AuditReactive.strict.commit();
		x.commit();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void execute() throws SQLException
	{
		AuditReactive.strict.commit();
		x.execute(null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void rollback() throws SQLException
	{
		AuditReactive.strict.commit();
		x.rollback();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void rollback_SavePoint() throws SQLException
	{
		AuditReactive.strict.commit();
		x.rollback(null);
	}
}
