package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.rowset.WebRowSet;
import java.io.*;
import java.sql.SQLException;

public class ConnectionPoolDataSourceTest
{
	WebRowSet x = (WebRowSet) TestTools.createProxy(WebRowSet.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void readXML_InputStream() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.readXml((InputStream) null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void readXML_Reader() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.readXml((Reader) null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void writeXML_OutputStream() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.writeXml((OutputStream) null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void writeXML_RS_OutputStream() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.writeXml(null,(OutputStream) null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void writeXML_Writer() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.writeXml((Writer) null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void writeXML_RS_Writer() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.writeXml(null,(Writer) null);
	}
}
