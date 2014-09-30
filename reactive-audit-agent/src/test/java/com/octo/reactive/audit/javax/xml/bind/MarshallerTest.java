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

import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class MarshallerTest
{
	private final Marshaller x = (Marshaller) TestTools.createProxy(Marshaller.class);

	@Test(expected = FileReactiveAuditException.class)
	public void marshal_File()
			throws JAXBException
	{
		TestTools.strict.commit();
		x.marshal(null, IOTestTools.getTempFile());
	}

	@Test(expected = FileReactiveAuditException.class)
	public void marshal_OutputStream()
			throws JAXBException
	{
		TestTools.strict.commit();
		x.marshal(null, IOTestTools.getTempFileOutputStream());
	}

	@Test(expected = FileReactiveAuditException.class)
	public void marshal_Writer()
			throws JAXBException
	{
		TestTools.strict.commit();
		x.marshal(null, IOTestTools.getTempFileWriter());
	}

}
