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

package com.octo.reactive.audit.javax.sql.rowset.spi;

import com.octo.reactive.audit.AbstractNetworkAudit;
import com.octo.reactive.audit.java.io.AbstractWriterAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.sql.rowset.WebRowSet;
import java.io.Writer;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 1
@Aspect
public class XmlWriterAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.sql.rowset.spi.XmlWriter.writeXML(javax.sql.rowset.WebRowSet,java.io.Writer)) && args(wrs,out)")
	public void commit(JoinPoint thisJoinPoint, WebRowSet wrs, Writer out)
	{
		final ExceptionFactory ef = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ef != null) super.logLatency(HIGH, thisJoinPoint, ef);
	}
}
