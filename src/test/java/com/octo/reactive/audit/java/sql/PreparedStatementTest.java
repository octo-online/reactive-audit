package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by pprados on 19/05/2014.
 */
public class PreparedStatementTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void execute() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.off.commit();
		Connection c = DriverManagerTest.getDBConnection();
		AuditReactive.strict.commit();
		c.prepareStatement("select * FROM sys.systables where 2=1").execute();
	}
}
