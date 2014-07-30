package com.octo.reactive.audit.javax.xml.ws.spi;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;
import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import java.io.IOException;

public class InvokerTest
{
	SAXParser x = (SAXParser) TestTools.createProxy(SAXParser.class);

	@Test(expected = NetworkAuditReactiveException.class)
	public void parse_File_DefaultHandler() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile(),(DefaultHandler)null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void parse_File_HandlerBase() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile(),(HandlerBase)null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void parse_InputStream_DefaultHandler() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFileInputStream(),(DefaultHandler)null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void parse_InputStream_HandlerBase() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFileInputStream(),(HandlerBase)null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void parse_InputStream_HandlerBase_S() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFileInputStream(),(HandlerBase)null,null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void parse_String_DefaultHandler() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile().toURI().toString(),(DefaultHandler)null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void parse_String_HandlerBase() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile().toURI().toString(),(HandlerBase)null);
	}

}
