package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerTest
{
	public static final String DB = "jdbc:derby:memory:myDB;create=true";

	public static Connection getDBConnection() throws SQLException
	{
		return DriverManager.getConnection(DB, "SA", "");
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getConnection() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		Connection c = getDBConnection();
	}
}
