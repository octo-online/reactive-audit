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

import com.octo.reactive.audit.AbstractCPUAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 47
@Aspect
public class VectorAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.Vector.copyInto(..))")
	public void copyInto(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.trimToSize(..))")
	public void trimToSize(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.ensureCapacity(..))")
	public void ensureCapacity(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.setSize(..))")
	public void setSize(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.capacity())")
	public void capacity(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.size())")
	public void size(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.isEmpty())")
	public void isEmpty(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.contains(..))")
	public void contains(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	// 2 methods
	@Before("call(* java.util.Vector.indexOf(..))")
	public void indexOf(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	// 2 methods
	@Before("call(* java.util.Vector.lastIndexOf(..))")
	public void lastIndexOf(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.elementAt(..))")
	public void elementAt(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.firstElement(..))")
	public void firstElement(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.lastElement(..))")
	public void lastElement(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.setElementAt(..))")
	public void setElementAt(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.removeElementAt(..))")
	public void removeElementAt(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.insertElementAt(..))")
	public void insertElementAt(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.addElement(..))")
	public void addElement(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.removeElement(..))")
	public void removeElement(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.removeAllElements(..))")
	public void removeAllElements(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.clone())")
	public void clone(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	// 2 methods
	@Before("call(* java.util.Vector.toArray(..))")
	public void toArray(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.get(..))")
	public void get(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.set(..))")
	public void set(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	// 2 methods
	@Before("call(* java.util.Vector.add(..))")
	public void add(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	// 2 methods
	@Before("call(* java.util.Vector.remove(..))")
	public void remove(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.clear())")
	public void clear(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.containsAll(..))")
	public void containsAll(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	// 2 methods
	@Before("call(* java.util.Vector.addAll(..))")
	public void addAll(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.removeAll(..))")
	public void removeAll(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.retainAll(..))")
	public void retainAll(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.equals(..))")
	public void equals(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.hashCode(..))")
	public void hashCode(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.toString(..))")
	public void toString(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.subList(..))")
	public void subList(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.removeRange(..))")
	public void removeRange(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.listIterator(..))")
	public void listIterator(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.iterator(..))")
	public void iterator(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.forEach(..))")
	public void forEach(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.removeIf(..))")
	public void removeIf(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.replaceAll(..))")
	public void replaceAll(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Vector.sort(..))")
	public void sort(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
}
