package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pprados on 19/05/2014.
 */
public class ResultSetTest
{
	private ResultSet getResultSet() throws SQLException
	{
		TestTools.push();
		Connection c = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
		ResultSet rs = c.prepareStatement("select * FROM sys.systables where 2=1").executeQuery();
		TestTools.pop();
		return rs;
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void absolute() throws InterruptedException, ClassNotFoundException, SQLException
	{
		ResultSet rs = getResultSet();
		rs.absolute(1);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void afterLast() throws InterruptedException, ClassNotFoundException, SQLException
	{
		ResultSet rs = getResultSet();
		rs.afterLast();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void beforeFirst() throws InterruptedException, ClassNotFoundException, SQLException
	{
		ResultSet rs = getResultSet();
		rs.beforeFirst();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void deleteRow() throws InterruptedException, ClassNotFoundException, SQLException
	{
		ResultSet rs = getResultSet();
		rs.deleteRow();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void first() throws InterruptedException, ClassNotFoundException, SQLException
	{
		ResultSet rs = getResultSet();
		rs.first();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void last() throws InterruptedException, ClassNotFoundException, SQLException
	{
		ResultSet rs = getResultSet();
		rs.last();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void next() throws InterruptedException, ClassNotFoundException, SQLException
	{
		ResultSet rs = getResultSet();
		rs.next();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void previous() throws InterruptedException, ClassNotFoundException, SQLException
	{
		ResultSet rs = getResultSet();
		rs.previous();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void refreshRow() throws InterruptedException, ClassNotFoundException, SQLException
	{
		ResultSet rs = getResultSet();
		rs.refreshRow();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void relative() throws InterruptedException, ClassNotFoundException, SQLException
	{
		ResultSet rs = getResultSet();
		rs.relative(1);
	}
}
