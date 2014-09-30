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

package com.octo.reactive.audit.javax.xml.ws;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.junit.Test;
import org.w3c.dom.Element;

import javax.xml.transform.Source;
import javax.xml.ws.Binding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.spi.http.HttpContext;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class EndpointTest
{
	final private Endpoint x = new Endpoint()
	{

		@Override
		public Binding getBinding()
		{
			return null;
		}

		@Override
		public Object getImplementor()
		{
			return null;
		}

		@Override
		public void publish(String address)
		{

		}

		@Override
		public void publish(Object serverContext)
		{

		}

		@Override
		public void stop()
		{

		}

		@Override
		public boolean isPublished()
		{
			return false;
		}

		@Override
		public List<Source> getMetadata()
		{
			return null;
		}

		@Override
		public void setMetadata(List<Source> metadata)
		{

		}

		@Override
		public Executor getExecutor()
		{
			return null;
		}

		@Override
		public void setExecutor(Executor executor)
		{

		}

		@Override
		public Map<String, Object> getProperties()
		{
			return null;
		}

		@Override
		public void setProperties(Map<String, Object> properties)
		{

		}

		@Override
		public EndpointReference getEndpointReference(Element... referenceParameters)
		{
			return null;
		}

		@Override
		public <T extends EndpointReference> T getEndpointReference(Class<T> clazz, Element... referenceParameters)
		{
			return null;
		}
	};

	@Test(expected = NetworkReactiveAuditException.class)
	public void publish_HttpContext()
	{
        TestTools.strict.commit();
		x.publish((HttpContext) null);
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void publish_Object()
	{
        TestTools.strict.commit();
		x.publish((Object) null);
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void publish_String()
	{
        TestTools.strict.commit();
		x.publish((String) null);
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void publish_String_Object()
	{
        TestTools.strict.commit();
		Endpoint.publish(null, null);
	}

	@Test(expected = NetworkReactiveAuditException.class)
	public void publish_String_Object_WebServiceFeature()
	{
        TestTools.strict.commit();
		Endpoint.publish(null, null, (WebServiceFeature) null);
	}

}
