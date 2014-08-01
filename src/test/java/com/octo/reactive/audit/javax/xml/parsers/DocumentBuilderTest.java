package com.octo.reactive.audit.javax.xml.parsers;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;

public class DocumentBuilderTest
{
	final private DocumentBuilder x = new DocumentBuilder()
	{

		@Override
		public Document parse(InputSource inputSource) throws SAXException, IOException
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
		public void setEntityResolver(EntityResolver entityResolver)
		{

		}

		@Override
		public void setErrorHandler(ErrorHandler errorHandler)
		{

		}

		@Override
		public Document newDocument()
		{
			return null;
		}

		@Override
		public DOMImplementation getDOMImplementation()
		{
			return null;
		}
	};

	@Test(expected = FileAuditReactiveException.class)
	public void parse_File() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void parse_String() throws IOException, SAXException
	{
		AuditReactive.strict.commit();
		x.parse(IOTestTools.getTempFile().toURI().toString());
	}

}
