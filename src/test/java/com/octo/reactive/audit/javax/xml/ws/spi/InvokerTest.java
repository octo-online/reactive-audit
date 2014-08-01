package com.octo.reactive.audit.javax.xml.ws.spi;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import javax.xml.ws.WebServiceContext;
import javax.xml.ws.spi.Invoker;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokerTest
{
	private final Invoker x = new Invoker()
	{

		@Override
		public void inject(WebServiceContext webServiceContext)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
		{

		}

		@Override
		public Object invoke(Method m, Object... args)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
		{
			return null;
		}
	};

	@Test(expected = NetworkAuditReactiveException.class)
	public void invoke() throws InvocationTargetException, IllegalAccessException
	{
		AuditReactive.strict.commit();
		x.invoke(null, null);
	}

}
