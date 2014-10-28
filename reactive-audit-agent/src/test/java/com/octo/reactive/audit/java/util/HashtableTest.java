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

package com.octo.reactive.audit.java.util;

import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.junit.Test;

import java.util.Hashtable;

public class HashtableTest
{
	@Test(expected = ReactiveAuditException.class)
	public void size()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.size();
	}
	@Test(expected = ReactiveAuditException.class)
	public void isEmpty()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.isEmpty();
	}
	@Test(expected = ReactiveAuditException.class)
	public void keys()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.keys();
	}
	@Test(expected = ReactiveAuditException.class)
	public void elements()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.elements();
	}
	@Test(expected = ReactiveAuditException.class)
	public void contains()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.contains(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void containsValue()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.containsValue(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void containsKey()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.containsKey(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void get()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.get(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void put()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.put(null,null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void remove()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.remove(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void putAll()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.putAll(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void clear()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
        TestTools.strict.commit();
		hash.clear();
	}
	@Test(expected = ReactiveAuditException.class)
	public void cloneThis()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.clone();
	}
	@Test(expected = ReactiveAuditException.class)
	public void toStringThis()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.toString();
	}
	@Test(expected = ReactiveAuditException.class)
	public void equals()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.equals(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void hashCodeThis()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.hashCode();
	}
	@Test(expected = ReactiveAuditException.class)
	public void getOrDefault()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.getOrDefault(null,null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void forEach()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.forEach(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void replaceAll()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.replaceAll(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void putIfAbsent()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.putIfAbsent(null,null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void replace()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.replace(null,null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void computeIfAbsent()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.computeIfAbsent(null,null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void computeIfPresent()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.computeIfPresent(null,null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void compute()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.compute(null,null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void merge()
	{
		ReactiveAudit.off.commit();
		Hashtable hash=new Hashtable();
		TestTools.strict.commit();
		hash.merge(null,null,null);
	}

}
