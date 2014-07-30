package com.octo.reactive.audit.javax.sql.rowset;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.sql.SQLException;

public class JdbcRowSetTest
{
	CachedRowSet x = (CachedRowSet) TestTools.createProxy(CachedRowSet.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void acceptChanges() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.acceptChanges();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void acceptChanges_Connection() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.acceptChanges(null);
	}
	@Test(expected = NetworkAuditReactiveException.class)
	public void commit() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.commit();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void execute() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.execute(null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void rollback() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.rollback();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void rollback_SavePoint() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.rollback(null);
	}
}
