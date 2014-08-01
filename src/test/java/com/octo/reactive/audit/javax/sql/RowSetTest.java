package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.RowSet;
import java.sql.SQLException;

public class RowSetTest
{
	private final RowSet x = (RowSet) TestTools.createProxy(RowSet.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void execute() throws SQLException
	{
		AuditReactive.strict.commit();
		x.execute();
	}

}
