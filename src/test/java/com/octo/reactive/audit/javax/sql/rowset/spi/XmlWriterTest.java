package com.octo.reactive.audit.javax.sql.rowset.spi;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import javax.sql.rowset.spi.XmlWriter;
import java.sql.SQLException;

public class XmlWriterTest
{
	private final XmlWriter x = (XmlWriter) TestTools.createProxy(XmlWriter.class);

	@Test(expected = FileAuditReactiveException.class)
	public void writeXML() throws SQLException
	{
		AuditReactive.strict.commit();
		x.writeXML(null, IOTestTools.getTempFileWriter());
	}

}
