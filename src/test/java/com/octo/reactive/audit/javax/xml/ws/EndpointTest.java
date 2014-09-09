package com.octo.reactive.audit.javax.xml.ws;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
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

	@Test(expected = NetworkAuditReactiveException.class)
	public void publish_HttpContext()
	{
		AuditReactive.strict.commit();
		x.publish((HttpContext) null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void publish_Object()
	{
		AuditReactive.strict.commit();
		x.publish((Object) null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void publish_String()
	{
		AuditReactive.strict.commit();
		x.publish((String) null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void publish_String_Object()
	{
		AuditReactive.strict.commit();
		Endpoint.publish(null, null);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void publish_String_Object_WebServiceFeature()
	{
		AuditReactive.strict.commit();
		Endpoint.publish(null, null, (WebServiceFeature)null);
	}

}
