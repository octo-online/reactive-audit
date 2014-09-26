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

package com.octo.reactive.audit.java.nio.file;

import com.octo.reactive.audit.AbstractFileAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 12
@Aspect
public class FilesAudit extends AbstractFileAudit
{
	@Before("call(* java.nio.file.Files.copy(..))")
	public void copy(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(int java.nio.file.Files.list(..))")
	public void list(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(byte[] java.nio.file.Files.readAllBytes(..))")
	public void readAllBytes(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(java.util.List<String> java.nio.file.Files.readAllLines(..))")
	public void readAllLines(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(java.nio.file.Path java.nio.file.Files.walkFileTree(..))")
	public void walkFileTree(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(java.nio.file.Path java.nio.file.Files.write(..))")
	public void write(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
