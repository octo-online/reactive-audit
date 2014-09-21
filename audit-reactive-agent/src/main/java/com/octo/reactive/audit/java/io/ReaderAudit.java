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

import com.octo.reactive.audit.FileTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.Reader;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 5
@Aspect
public class ReaderAudit extends AbstractReaderAudit
{
	@Before("call(* java.io.Reader+.read(..))")
	public void read(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			Reader reader = (Reader) thisJoinPoint.getTarget();
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(reader));
		}
		latency(HIGH, thisJoinPoint, (Reader) thisJoinPoint.getTarget());
	}

	@Before("call(* java.io.Reader+.skip(..))")
	public void skip(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint, (Reader) thisJoinPoint.getTarget());
	}

}
