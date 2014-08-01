package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.SQLOutput;

public class SQLOutputTest
{
	private final SQLOutput output = (SQLOutput) TestTools.createProxy(SQLOutput.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void write() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		output.writeBoolean(true);
	}

}
