package com.octo.reactive.audit.javax.sql.rowset.spi;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import javax.sql.rowset.spi.XmlReader;
import java.sql.SQLException;

public class XmlReaderTest
{
	private final XmlReader x = (XmlReader) TestTools.createProxy(XmlReader.class);

	@Test(expected = FileAuditReactiveException.class)
	public void readXML() throws SQLException
	{
		AuditReactive.strict.commit();
		x.readXML(null, IOTestTools.getTempFileReader());
	}

}
