package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.ConnectionPoolDataSource;
import java.sql.SQLException;

public class ConnectionPoolDataSourceTest
{
	private final ConnectionPoolDataSource x = (ConnectionPoolDataSource) TestTools.createProxy(
			ConnectionPoolDataSource.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void getPooledConnection() throws SQLException
	{
		AuditReactive.strict.commit();
		x.getPooledConnection();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getPooledConnection_up() throws SQLException
	{
		AuditReactive.strict.commit();
		x.getPooledConnection(null, null);
	}

}
