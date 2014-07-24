package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pprados on 19/05/2014.
 */
public class ConnectionTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void close() throws SQLException
	{
		AuditReactive.off.commit();
		Connection c = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
		AuditReactive.strict.commit();
		c.close();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void commit() throws SQLException
	{
		AuditReactive.off.commit();
		Connection c = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
		AuditReactive.strict.commit();
		c.commit();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void rollback() throws SQLException
	{
		AuditReactive.off.commit();
		Connection c = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
		AuditReactive.strict.commit();
		c.rollback();
	}
}
