package com.octo.reactive.audit.javax.xml.parsers;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;

public class SAXParserTest
{
	DocumentBuilder x = (DocumentBuilder) TestTools.createProxy(DocumentBuilder.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void parse_File() throws InterruptedException, IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile());
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void parse_String() throws InterruptedException, IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile().toURI().toURL().toExternalForm());
	}

}
