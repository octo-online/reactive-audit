package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.PooledConnection;
import java.io.IOException;
import java.sql.SQLException;

public class RowSetTest
{
	PooledConnection x = (PooledConnection) TestTools.createProxy(PooledConnection.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void getConnection() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.getConnection();
	}

}
