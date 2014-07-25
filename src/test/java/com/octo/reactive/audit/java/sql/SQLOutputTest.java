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
public class SQLOutputTest
{
	SQLOutput output = new SQLOutput()
	{
		@Override
		public void writeString(String x) throws SQLException
		{

		}

		@Override
		public void writeBoolean(boolean x) throws SQLException
		{

		}

		@Override
		public void writeByte(byte x) throws SQLException
		{

		}

		@Override
		public void writeShort(short x) throws SQLException
		{

		}

		@Override
		public void writeInt(int x) throws SQLException
		{

		}

		@Override
		public void writeLong(long x) throws SQLException
		{

		}

		@Override
		public void writeFloat(float x) throws SQLException
		{

		}

		@Override
		public void writeDouble(double x) throws SQLException
		{

		}

		@Override
		public void writeBigDecimal(BigDecimal x) throws SQLException
		{

		}

		@Override
		public void writeBytes(byte[] x) throws SQLException
		{

		}

		@Override
		public void writeDate(Date x) throws SQLException
		{

		}

		@Override
		public void writeTime(Time x) throws SQLException
		{

		}

		@Override
		public void writeTimestamp(Timestamp x) throws SQLException
		{

		}

		@Override
		public void writeCharacterStream(Reader x) throws SQLException
		{

		}

		@Override
		public void writeAsciiStream(InputStream x) throws SQLException
		{

		}

		@Override
		public void writeBinaryStream(InputStream x) throws SQLException
		{

		}

		@Override
		public void writeObject(SQLData x) throws SQLException
		{

		}

		@Override
		public void writeRef(Ref x) throws SQLException
		{

		}

		@Override
		public void writeBlob(Blob x) throws SQLException
		{

		}

		@Override
		public void writeClob(Clob x) throws SQLException
		{

		}

		@Override
		public void writeStruct(Struct x) throws SQLException
		{

		}

		@Override
		public void writeArray(Array x) throws SQLException
		{

		}

		@Override
		public void writeURL(URL x) throws SQLException
		{

		}

		@Override
		public void writeNString(String x) throws SQLException
		{

		}

		@Override
		public void writeNClob(NClob x) throws SQLException
		{

		}

		@Override
		public void writeRowId(RowId x) throws SQLException
		{

		}

		@Override
		public void writeSQLXML(SQLXML x) throws SQLException
		{

		}
	};

	@Test(expected = NetworkAuditReactiveException.class)
	public void write() throws InterruptedException, ClassNotFoundException, SQLException
	{
		AuditReactive.strict.commit();
		output.writeBoolean(true);
	}

}
