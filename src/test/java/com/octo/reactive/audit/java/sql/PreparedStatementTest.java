package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatementTest
{
	private final PreparedStatement stm = (PreparedStatement) TestTools.createProxy(PreparedStatement.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void execute() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		stm.execute();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void executeQuery() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		stm.executeQuery();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void executeLargeUpdate() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		stm.executeLargeUpdate();
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void executeUpdate() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		stm.executeUpdate();
	}
}
