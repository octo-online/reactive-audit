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

package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;

public class UnmarshallerTest
{
	private final Unmarshaller x = (Unmarshaller) TestTools.createProxy(Unmarshaller.class);

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_File() throws JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFile());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_InputStream() throws JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFileInputStream());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_Reader() throws JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFileReader());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void unmarshal_URL() throws MalformedURLException, JAXBException
	{
		AuditReactive.strict.commit();
		x.unmarshal(IOTestTools.getTempFile().toURI().toURL());
	}


}
