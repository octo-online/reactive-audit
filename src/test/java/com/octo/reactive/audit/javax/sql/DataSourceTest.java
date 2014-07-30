package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.ConnectionPoolDataSource;
import java.io.*;
import java.sql.SQLException;

public class DataSourceTest
{
	ConnectionPoolDataSource x = (ConnectionPoolDataSource) TestTools.createProxy(ConnectionPoolDataSource.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void getPooledConnection() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.getPooledConnection();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getPooledConnection_up() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.getPooledConnection(null,null);
	}

}
