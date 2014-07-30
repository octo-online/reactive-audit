package com.octo.reactive.audit.javax.sql.rowset;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.rowset.spi.XmlWriter;
import java.io.IOException;
import java.sql.SQLException;

public class CachedRowSetTest
{
	XmlWriter x = (XmlWriter) TestTools.createProxy(XmlWriter.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void writeXML() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.writeXML(null, null);
	}

}
