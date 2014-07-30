package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.RowSet;
import java.io.IOException;
import java.sql.SQLException;

public class RowSetReaderTest
{
	RowSet x = (RowSet) TestTools.createProxy(RowSet.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void execute() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.execute();
	}

}
