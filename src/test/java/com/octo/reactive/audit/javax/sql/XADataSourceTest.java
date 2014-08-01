package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.XADataSource;
import java.sql.SQLException;

public class XADataSourceTest
{
	private final XADataSource x = (XADataSource) TestTools.createProxy(XADataSource.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void getXAConnection() throws SQLException
	{
		AuditReactive.strict.commit();
		x.getXAConnection();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getXAConnection_up() throws SQLException
	{
		AuditReactive.strict.commit();
		x.getXAConnection(null, null);
	}

}
