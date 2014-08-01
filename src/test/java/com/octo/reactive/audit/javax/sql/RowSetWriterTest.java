package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.RowSetWriter;
import java.sql.SQLException;

public class RowSetWriterTest
{
	private final RowSetWriter x = (RowSetWriter) TestTools.createProxy(RowSetWriter.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void readData() throws SQLException
	{
		AuditReactive.strict.commit();
		x.writeData(null);
	}

}
