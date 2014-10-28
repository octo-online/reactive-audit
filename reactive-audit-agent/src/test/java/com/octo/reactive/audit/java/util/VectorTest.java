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

import java.util.Vector;

public class VectorTest
{
	@Test(expected = ReactiveAuditException.class)
	public void copyInto()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.copyInto(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void trimToSize()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.trimToSize();
	}
	@Test(expected = ReactiveAuditException.class)
	public void ensureCapacity()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.ensureCapacity(0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void setSize()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.setSize(0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void capacity()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.capacity();
	}
	@Test(expected = ReactiveAuditException.class)
	public void size()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.size();
	}
	@Test(expected = ReactiveAuditException.class)
	public void isEmpty()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.isEmpty();
	}
	@Test(expected = ReactiveAuditException.class)
	public void contain()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.contains(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void indexOf_1()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.indexOf(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void indexOf_2()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.indexOf(null, 0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void lastIndexOf_1()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.lastIndexOf(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void lastIndexOf_2()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.lastIndexOf(null, 0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void elementAt()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.elementAt(0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void firstElement()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.firstElement();
	}
	@Test(expected = ReactiveAuditException.class)
	public void lastElement()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.lastElement();
	}
	@Test(expected = ReactiveAuditException.class)
	public void setElementAt()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.setElementAt(null, 0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void removeElementAt()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.removeElementAt(0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void insertElementAt()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.insertElementAt(null, 0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void addElement()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.addElement(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void removeElement()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.removeElement(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void removeAllElements()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.removeAllElements();
	}
	@Test(expected = ReactiveAuditException.class)
	public void cloneThis()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.clone();
	}
	@Test(expected = ReactiveAuditException.class)
	public void toArray_1()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.toArray();
	}
	@Test(expected = ReactiveAuditException.class)
	public void toArray_2()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.toArray(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void get()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.get(0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void set()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.set(0, null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void add_1()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.add(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void add_2()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.add(0, null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void remove_1()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.remove(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void remove_2()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.remove(0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void clear()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.clear();
	}
	@Test(expected = ReactiveAuditException.class)
	public void containsAll()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.containsAll(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void addAll_1()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.addAll(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void addAll_2()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.addAll(0, null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void removeAll()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.removeAll(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void retainAll()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.retainAll(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void equalsThis()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.equals(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void hashCodeThis()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.hashCode();
	}
	@Test(expected = ReactiveAuditException.class)
	public void toStringThis()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.toString();
	}
	@Test(expected = ReactiveAuditException.class)
	public void subList()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.subList(0, 0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void removeRange()
	{
		ReactiveAudit.off.commit();
		class XVector<E> extends Vector
		{
			public void removeRange(int fromIndex, int toIndex)
			{
				super.removeRange(fromIndex,toIndex);
			}
		};
		XVector vector=new XVector();

		TestTools.strict.commit();
		vector.removeRange(0,0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void listIterator_1()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.listIterator();
	}
	@Test(expected = ReactiveAuditException.class)
	public void listIterator_2()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.listIterator(0);
	}
	@Test(expected = ReactiveAuditException.class)
	public void iterator()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.iterator();
	}
	@Test(expected = ReactiveAuditException.class)
	public void forEach()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.forEach(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void removeIf()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.removeIf(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void replaceAll()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.replaceAll(null);
	}
	@Test(expected = ReactiveAuditException.class)
	public void sort()
	{
		ReactiveAudit.off.commit();
		Vector vector=new Vector();
		TestTools.strict.commit();
		vector.sort(null);
	}
}
