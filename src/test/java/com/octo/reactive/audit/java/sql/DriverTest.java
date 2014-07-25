package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by pprados on 19/05/2014.
 */
public class DriverTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws SQLException
	{
		Driver d = DriverManager.getDriver(DriverManagerTest.DB);
		d.connect("", null);
	}
}
