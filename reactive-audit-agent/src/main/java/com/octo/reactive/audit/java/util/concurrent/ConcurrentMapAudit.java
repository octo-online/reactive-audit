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

import com.octo.reactive.audit.AbstractCPUAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 13
@Aspect
public class ConcurrentMapAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.concurrent.ConcurrentMap.compute(..) )")
	public void compute(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.concurrent.ConcurrentMap.put(..) )")
	public void put(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.concurrent.ConcurrentMap.putIfAbsent(..) )")
	public void putIfAbsent(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.concurrent.ConcurrentMap.putAll(..) )")
	public void putAll(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.concurrent.ConcurrentMap.remove(..) )")
	public void remove(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.concurrent.ConcurrentMap.clear(..) )")
	public void clear(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	// 3 methods
	@Before("call(* java.util.concurrent.ConcurrentMap.compute*(..) )")
	public void computeIfAbsent(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.concurrent.ConcurrentMap.merge(..) )")
	public void merge(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.concurrent.ConcurrentMap.replace(..) )")
	public void replace(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.concurrent.ConcurrentMap.replaceAll(..) )")
	public void replaceAll(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
	@Before("call(* java.util.concurrent.ConcurrentMap.equals(..) )")
	public void equals(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
}
