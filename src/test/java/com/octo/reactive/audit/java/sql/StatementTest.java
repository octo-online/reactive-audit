package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest
{
	private final Statement statement = (Statement) TestTools.createProxy(Statement.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void execute() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		statement.execute(null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getMoreResults() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		statement.getMoreResults();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getResultSet() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		statement.getResultSet();
	}

}
