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
