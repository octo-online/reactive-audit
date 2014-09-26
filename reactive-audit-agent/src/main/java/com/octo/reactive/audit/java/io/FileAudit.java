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

package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractFileAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 24
@Aspect
public class FileAudit extends AbstractFileAudit
{
	@Before("call(* java.io.File+.createNewFile())")
	public void createNewFile(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.createTempFile(..))")
	public void createTempFile(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.delete(..))")
	public void delete(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.exists())")
	public void exists(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.getFreeSpace(..))")
	public void getFreeSpace(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.getTotalSpace(..))")
	public void getTotalSpace(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.getUsableSpace(..))")
	public void getUsableSpace(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.list(..))")
	public void list(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.listFiles(..))")
	public void listFiles(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.mkdir*(..))")
	public void mkdir(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.renameTo(..))")
	public void renameTo(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.set*(..))")
	public void setX(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

}
