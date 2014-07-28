package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DriverTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws SQLException
	{
		Driver d = new Driver()
		{
			@Override
			public Connection connect(String url, Properties info) throws SQLException
			{
				return null;
			}

			@Override
			public boolean acceptsURL(String url) throws SQLException
			{
				return false;
			}

			@Override
			public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException
			{
				return new DriverPropertyInfo[0];
			}

			@Override
			public int getMajorVersion()
			{
				return 0;
			}

			@Override
			public int getMinorVersion()
			{
				return 0;
			}

			@Override
			public boolean jdbcCompliant()
			{
				return false;
			}

			@Override
			public Logger getParentLogger() throws SQLFeatureNotSupportedException
			{
				return null;
			}
		};
		d.connect("", null);
	}
}
