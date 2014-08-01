package com.octo.reactive.audit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Helper for unit test.
 */
public final class TestTools
{
	private TestTools()
	{
	}

	public static void push()
	{
		AuditReactive.config.push();
		AuditReactive.off.commit();
	}

	public static void pop()
	{
		AuditReactive.config.pop();
	}

	public static Object createProxy(Class<?> clazz)
	{
		return Proxy.newProxyInstance(clazz.getClassLoader(),
		                              new Class[]{clazz},
		                              new InvocationHandler()
		                              {
			                              @Override
			                              public Object invoke(Object proxy, Method method, Object[] args)
					                              throws Throwable
			                              {
				                              return null;
			                              }
		                              });
	}
}
