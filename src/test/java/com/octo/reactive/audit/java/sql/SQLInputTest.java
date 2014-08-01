package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.SQLInput;

public class SQLInputTest
{
	private final SQLInput input = (SQLInput) TestTools.createProxy(SQLInput.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void read() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		input.readShort();
	}

}
