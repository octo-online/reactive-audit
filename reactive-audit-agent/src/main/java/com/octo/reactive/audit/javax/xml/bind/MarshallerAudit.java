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

package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.java.io.AbstractOutputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractWriterAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.OutputStream;
import java.io.Writer;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 3
@Aspect
public class MarshallerAudit extends AbstractFileAudit
{
	@Before("call(* javax.xml.bind.Marshaller.marshal(Object,java.io.File))")
	public void marshal(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* javax.xml.bind.Marshaller.marshal(Object,java.io.OutputStream)) && args(o,out)")
	public void marshal(JoinPoint thisJoinPoint, Object o, OutputStream out)
	{
		final ExceptionFactory ef =
				AbstractOutputStreamAudit.latencyOutputStream(config, HIGH, thisJoinPoint, out);
		if (ef != null) super.logLatency(HIGH, thisJoinPoint,ef);
	}

	@Before("call(* javax.xml.bind.Marshaller.marshal(Object,java.io.Writer)) && args(o,out)")
	public void marshal(JoinPoint thisJoinPoint, Object o, Writer out)
	{
		final ExceptionFactory ef = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ef != null) super.logLatency(HIGH, thisJoinPoint, ef);
	}
}
