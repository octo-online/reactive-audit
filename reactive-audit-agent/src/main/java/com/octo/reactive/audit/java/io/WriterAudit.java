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

import java.io.Writer;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 10
@Aspect
public class WriterAudit extends AbstractWriterAudit
{
	@Before("call(* java.io.Writer.append(..))")
	public void append(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			Writer writer = (Writer) thisJoinPoint.getTarget();
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(HIGH, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}

	@Before("call(* java.io.Writer.write(..))")
	public void write(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			Writer writer = (Writer) thisJoinPoint.getTarget();
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(HIGH, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}

	@Before("call(* java.io.Writer.flush(..))")
	public void flush(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			Writer writer = (Writer) thisJoinPoint.getTarget();
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(HIGH, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}

	@Before("call(* java.io.Writer.close())")
	public void close(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			Writer writer = (Writer) thisJoinPoint.getTarget();
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(LOW, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}

}
