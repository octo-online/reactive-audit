package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;

/**
 * Created by pprados on 19/05/2014.
 */
public class SQLInputTest
{
	SQLInput input = new SQLInput()
	{

		@Override
		public String readString() throws SQLException
		{
			return null;
		}

		@Override
		public boolean readBoolean() throws SQLException
		{
			return false;
		}

		@Override
		public byte readByte() throws SQLException
		{
			return 0;
		}

		@Override
		public short readShort() throws SQLException
		{
			return 0;
		}

		@Override
		public int readInt() throws SQLException
		{
			return 0;
		}

		@Override
		public long readLong() throws SQLException
		{
			return 0;
		}

		@Override
		public float readFloat() throws SQLException
		{
			return 0;
		}

		@Override
		public double readDouble() throws SQLException
		{
			return 0;
		}

		@Override
		public BigDecimal readBigDecimal() throws SQLException
		{
			return null;
		}

		@Override
		public byte[] readBytes() throws SQLException
		{
			return new byte[0];
		}

		@Override
		public Date readDate() throws SQLException
		{
			return null;
		}

		@Override
		public Time readTime() throws SQLException
		{
			return null;
		}

		@Override
		public Timestamp readTimestamp() throws SQLException
		{
			return null;
		}

		@Override
		public Reader readCharacterStream() throws SQLException
		{
			return null;
		}

		@Override
		public InputStream readAsciiStream() throws SQLException
		{
			return null;
		}

		@Override
		public InputStream readBinaryStream() throws SQLException
		{
			return null;
		}

		@Override
		public Object readObject() throws SQLException
		{
			return null;
		}

		@Override
		public Ref readRef() throws SQLException
		{
			return null;
		}

		@Override
		public Blob readBlob() throws SQLException
		{
			return null;
		}

		@Override
		public Clob readClob() throws SQLException
		{
			return null;
		}

		@Override
		public Array readArray() throws SQLException
		{
			return null;
		}

		@Override
		public boolean wasNull() throws SQLException
		{
			return false;
		}

		@Override
		public URL readURL() throws SQLException
		{
			return null;
		}

		@Override
		public NClob readNClob() throws SQLException
		{
			return null;
		}

		@Override
		public String readNString() throws SQLException
		{
			return null;
		}

		@Override
		public SQLXML readSQLXML() throws SQLException
		{
			return null;
		}

		@Override
		public RowId readRowId() throws SQLException
		{
			return null;
		}
	};

	@Test(expected = NetworkAuditReactiveException.class)
	public void read() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		input.readShort();
	}

}
