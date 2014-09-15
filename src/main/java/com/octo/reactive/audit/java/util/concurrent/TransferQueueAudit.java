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

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 2
@Aspect
public class TransferQueueAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.concurrent.TransferQueue.transfer(Object) )")
	public void transfer(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(* java.util.concurrent.TransferQueue.tryTransfer(Object,long,java.util.concurrent.TimeUnit) )")
	public void tryTransfer(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}
}
