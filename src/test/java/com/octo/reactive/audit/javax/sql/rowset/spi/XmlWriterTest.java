package com.octo.reactive.audit.javax.sql.rowset.spi;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.sql.rowset.spi.XmlReader;
import java.io.IOException;
import java.sql.SQLException;

public class XmlWriterTest
{
	XmlReader x = (XmlReader)TestTools.createProxy(XmlReader.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void readXML() throws InterruptedException, IOException, SQLException
	{
		AuditReactive.strict.commit();
		x.readXML(null,null);
	}

}
