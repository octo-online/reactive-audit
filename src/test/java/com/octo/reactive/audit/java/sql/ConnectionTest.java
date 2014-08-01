package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionTest
{
	private final Connection c = (Connection) TestTools.createProxy(Connection.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void close() throws SQLException
	{
		c.close();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void commit() throws SQLException
	{
		c.commit();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void rollback() throws SQLException
	{
		c.rollback();
	}
}
