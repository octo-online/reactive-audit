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
		public Document parse(InputSource inputSource)
				throws SAXException, IOException
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

	@Test(expected = FileReactiveAuditException.class)
	public void parse_File()
			throws IOException, SAXException
	{
        TestTools.strict.commit();
		x.parse(IOTestTools.getTempFile());
	}

	@Test(expected = FileReactiveAuditException.class)
	public void parse_String()
			throws IOException, SAXException
	{
        TestTools.strict.commit();
		x.parse(IOTestTools.getTempFile().toURI().toString());
	}

}
