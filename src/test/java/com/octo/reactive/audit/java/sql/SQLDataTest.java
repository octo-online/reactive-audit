package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.SQLData;
import java.sql.SQLException;

public class SQLDataTest
{
	private final SQLData data = (SQLData) TestTools.createProxy(SQLData.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void readSQL() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		data.readSQL(null, null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void writeSQL() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		data.writeSQL(null);
	}
}
