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

package com.octo.reactive.audit.javax.xml.soap;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SOAPConnectionTest
{
	private final SOAPConnection x = new SOAPConnection()
	{

		@Override
		public SOAPMessage call(SOAPMessage request, Object to)
				throws SOAPException
		{
			return null;
		}

		@Override
		public void close()
				throws SOAPException
		{

		}
	};

	@Test(expected = NetworkReactiveAuditException.class)
	public void call()
			throws SOAPException
	{
        TestTools.strict.commit();
		x.call(null, null);
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void get()
			throws SOAPException
	{
        TestTools.strict.commit();
		x.get(null);
	}

}
