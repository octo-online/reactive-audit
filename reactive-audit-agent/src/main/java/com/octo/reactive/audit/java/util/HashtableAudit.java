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

// Nb methods: 25
@Aspect
public class HashtableAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.Hashtable.size())")
	public void size(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.isEmpty())")
	public void isEmpty(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.keys())")
	public void keys(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.elements())")
	public void elements(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	// 3 methods
	@Before("call(* java.util.Hashtable.contains*(..))")
	public void contains(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.get(..))")
	public void get(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.put(..))")
	public void put(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.remove(..))")
	public void remove(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.putAll(..))")
	public void putAll(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.clear())")
	public void clear(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.clone())")
	public void clone(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.toString())")
	public void toString(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.equals(..))")
	public void equals(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.hashCode())")
	public void hashCode(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.getOrDefault(..))")
	public void getOrDefault(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.forEach(..))")
	public void forEach(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.replaceAll(..))")
	public void replaceAll(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.putIfAbsent(..))")
	public void putIfAbsent(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.replace(..))")
	public void replace(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.computeIfAbsent(..))")
	public void computeIfAbsent(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.computeIfPresent(..))")
	public void computeIfPresent(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.compute(..))")
	public void compute(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.Hashtable.merge(..))")
	public void merge(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
}
