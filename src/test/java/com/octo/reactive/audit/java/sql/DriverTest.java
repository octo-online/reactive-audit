package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.Driver;
import java.sql.SQLException;

public class DriverTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws SQLException
	{
		Driver d = (Driver) TestTools.createProxy(Driver.class);
		d.connect("", null);
	}
}
