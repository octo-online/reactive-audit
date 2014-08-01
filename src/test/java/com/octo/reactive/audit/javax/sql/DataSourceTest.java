package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceTest
{
	private final DataSource x = (DataSource) TestTools.createProxy(DataSource.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void getConnection() throws SQLException
	{
		AuditReactive.strict.commit();
		x.getConnection();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getConnection_up() throws SQLException
	{
		AuditReactive.strict.commit();
		x.getConnection(null, null);
	}

}
