package com.octo.reactive.audit.javax.sql.rowset;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.rowset.JdbcRowSet;
import java.io.IOException;
import java.sql.SQLException;

public class JdbcRowSetTest
{
	private final JdbcRowSet x = (JdbcRowSet) TestTools.createProxy(JdbcRowSet.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void commit() throws SQLException
	{
		AuditReactive.strict.commit();
		x.commit();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void rollback() throws SQLException
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
