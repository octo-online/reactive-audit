package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 * Created by pprados on 19/05/2014.
 */
public class SQLDataTest
{
	SQLData data = new SQLData()
	{

		@Override
		public String getSQLTypeName() throws SQLException
		{
			return null;
		}

		@Override
		public void readSQL(SQLInput stream, String typeName) throws SQLException
		{

		}

		@Override
		public void writeSQL(SQLOutput stream) throws SQLException
		{

		}
	};

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
