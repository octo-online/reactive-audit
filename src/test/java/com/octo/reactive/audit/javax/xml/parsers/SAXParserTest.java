package com.octo.reactive.audit.javax.xml.parsers;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import java.io.IOException;

public class SAXParserTest
{
	private final SAXParser x = new SAXParser()
	{

		@Override
		public Parser getParser() throws SAXException
		{
			return null;
		}

		@Override
		public XMLReader getXMLReader() throws SAXException
		{
			return null;
		}

		@Override
		public boolean isNamespaceAware()
		{
			return false;
		}

		@Override
		public boolean isValidating()
		{
			return false;
		}

		@Override
		public void setProperty(String s, Object o) throws SAXNotRecognizedException, SAXNotSupportedException
		{

		}

		@Override
		public Object getProperty(String s) throws SAXNotRecognizedException, SAXNotSupportedException
		{
			return null;
		}
	};

	@Test(expected = FileAuditReactiveException.class)
	public void parse_File_DefaultHandler() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile(), (DefaultHandler) null);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void parse_File_HandlerBase() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile(), (HandlerBase) null);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void parse_InputStream_DefaultHandler() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFileInputStream(), (DefaultHandler) null);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void parse_InputStream_HandlerBase() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFileInputStream(), (HandlerBase) null);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void parse_InputStream_HandlerBase_S() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFileInputStream(), (HandlerBase) null, null);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void parse_String_DefaultHandler() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile().toURI().toString(), (DefaultHandler) null);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void parse_String_HandlerBase() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile().toURI().toString(), (HandlerBase) null);
	}

}
