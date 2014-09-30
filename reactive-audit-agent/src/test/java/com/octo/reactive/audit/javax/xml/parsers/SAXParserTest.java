/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit.javax.xml.parsers;

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
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
		@SuppressWarnings("deprecation")
		public Parser getParser()
				throws SAXException
		{
			return null;
		}

		@Override
		public XMLReader getXMLReader()
				throws SAXException
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
		public void setProperty(String s, Object o)
				throws SAXNotRecognizedException, SAXNotSupportedException
		{

		}

		@Override
		public Object getProperty(String s)
				throws SAXNotRecognizedException, SAXNotSupportedException
		{
			return null;
		}
	};

	@Test(expected = FileReactiveAuditException.class)
	public void parse_File_DefaultHandler()
			throws IOException, SAXException
	{
        TestTools.strict.commit();
		x.parse(IOTestTools.getTempFile(), (DefaultHandler) null);
	}

	@Test(expected = FileReactiveAuditException.class)
	@SuppressWarnings("deprecation")
	public void parse_File_HandlerBase()
			throws IOException, SAXException
	{
        TestTools.strict.commit();
		x.parse(IOTestTools.getTempFile(), (HandlerBase) null);
	}

	@Test(expected = FileReactiveAuditException.class)
	public void parse_InputStream_DefaultHandler()
			throws IOException, SAXException
	{
        TestTools.strict.commit();
		x.parse(IOTestTools.getTempFileInputStream(), (DefaultHandler) null);
	}

	@Test(expected = FileReactiveAuditException.class)
	@SuppressWarnings("deprecation")
	public void parse_InputStream_HandlerBase()
			throws IOException, SAXException
	{
        TestTools.strict.commit();
		x.parse(IOTestTools.getTempFileInputStream(), (HandlerBase) null);
	}

	@Test(expected = FileReactiveAuditException.class)
	@SuppressWarnings("deprecation")
	public void parse_InputStream_HandlerBase_S()
			throws IOException, SAXException
	{
        TestTools.strict.commit();
		x.parse(IOTestTools.getTempFileInputStream(), (HandlerBase) null, null);
	}

	@Test(expected = FileReactiveAuditException.class)
	public void parse_String_DefaultHandler()
			throws IOException, SAXException
	{
        TestTools.strict.commit();
		x.parse(IOTestTools.getTempFile().toURI().toString(), (DefaultHandler) null);
	}

	@Test(expected = FileReactiveAuditException.class)
	@SuppressWarnings("deprecation")
	public void parse_String_HandlerBase()
			throws IOException, SAXException
	{
        TestTools.strict.commit();
		x.parse(IOTestTools.getTempFile().toURI().toString(), (HandlerBase) null);
	}

}
