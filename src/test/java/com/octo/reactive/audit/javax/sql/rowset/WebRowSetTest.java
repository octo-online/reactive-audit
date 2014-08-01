package com.octo.reactive.audit.javax.sql.rowset;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;

import javax.sql.rowset.WebRowSet;
import java.io.IOException;
import java.sql.SQLException;

public class WebRowSetTest
{
	private final WebRowSet x = (WebRowSet) TestTools.createProxy(WebRowSet.class);

	@Test(expected = AuditReactiveException.class)
	public void readXML_InputStream() throws IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.readXml(IOTestTools.getTempFileInputStream());
	}

	@Test(expected = AuditReactiveException.class)
	public void readXML_Reader() throws SQLException
	{
		AuditReactive.strict.commit();
		x.readXml(IOTestTools.getTempFileReader());
	}

	@Test(expected = AuditReactiveException.class)
	public void writeXML_OutputStream() throws IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.writeXml(IOTestTools.getTempFileOutputStream());
	}

	@Test(expected = AuditReactiveException.class)
	public void writeXML_RS_OutputStream() throws IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.writeXml(null, IOTestTools.getTempFileOutputStream());
	}

	@Test(expected = AuditReactiveException.class)
	public void writeXML_Writer() throws SQLException
	{
		AuditReactive.strict.commit();
		x.writeXml(IOTestTools.getTempFileWriter());
	}

	@Test(expected = AuditReactiveException.class)
	public void writeXML_RS_Writer() throws SQLException
	{
		AuditReactive.strict.commit();
		x.writeXml(null, IOTestTools.getTempFileWriter());
	}
}
