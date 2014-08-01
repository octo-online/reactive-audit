package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.RowSetReader;
import java.sql.SQLException;

public class RowSetReaderTest
{
	private final RowSetReader x = (RowSetReader) TestTools.createProxy(RowSetReader.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void readData() throws SQLException
	{
		AuditReactive.strict.commit();
		x.readData(null);
	}

}
