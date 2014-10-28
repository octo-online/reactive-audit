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

package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.CPUReactiveAuditException;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class ConcurrentMapTest
{
	@Test(expected = CPUReactiveAuditException.class)
	public void offer()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.compute(null,null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void put()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.put(null, null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void putAll()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.putAll(null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void remove()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.remove(null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void clear()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.clear();
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void computeIfAbsent()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.computeIfAbsent(null, null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void computeIfPresent()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.computeIfAbsent(null, null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void compute()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.compute(null, null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void merge()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.merge(null, null, null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void putIfAbsent()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.putIfAbsent(null, null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void replace_1()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.replace(null, null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void replace_2()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.replace(null, null, null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void replaceAll()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.replaceAll(null);
	}
	@Test(expected = CPUReactiveAuditException.class)
	public void equals()
	{
        TestTools.strict.commit();
		ConcurrentMap<Object,Object> q = new ConcurrentHashMap<>();
		q.equals(null);
	}

}
