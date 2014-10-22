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

package com.octo.reactive.audit.javax.sql.rowset;

import com.octo.reactive.audit.AbstractNetworkAudit;
import com.octo.reactive.audit.java.io.AbstractInputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractOutputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractReaderAudit;
import com.octo.reactive.audit.java.io.AbstractWriterAudit;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.ResultSet;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 6
@Aspect
public class WebRowSetAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.sql.rowset.WebRowSet.readXml(java.io.InputStream)) && args(in)")
	public void readXml(JoinPoint thisJoinPoint, InputStream in)
	{
		final ReactiveAuditException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, new  ExceptionFactory()
		{
			public ReactiveAuditException lazyException()
			{
				return ex;
			}
		});
	}

	@Before("call(* javax.sql.rowset.WebRowSet.readXml(java.io.Reader)) && args(in)")
	public void readXml(JoinPoint thisJoinPoint, Reader in)
	{
		final ExceptionFactory ef = AbstractReaderAudit.latencyReader(config, HIGH, thisJoinPoint, in);
		if (ef != null) super.logLatency(HIGH, thisJoinPoint, ef);
	}

	@Before("call(* javax.sql.rowset.WebRowSet.writeXml(java.io.OutputStream)) && args(out)")
	public void writeXml(JoinPoint thisJoinPoint, OutputStream out)
	{
		final ExceptionFactory ef = AbstractOutputStreamAudit.latencyOutputStream(config, HIGH, thisJoinPoint, out);
		if (ef != null) super.logLatency(HIGH, thisJoinPoint, ef);
	}

	@Before("call(* javax.sql.rowset.WebRowSet.writeXml(java.sql.ResultSet,java.io.OutputStream)) && args(r,out)")
	public void writeXml(JoinPoint thisJoinPoint, ResultSet r, OutputStream out)
	{
		final ExceptionFactory ef = AbstractOutputStreamAudit.latencyOutputStream(config, HIGH, thisJoinPoint, out);
		if (ef != null) super.logLatency(HIGH, thisJoinPoint, ef);
	}

	@Before("call(* javax.sql.rowset.WebRowSet.writeXml(java.sql.ResultSet,java.io.Writer)) && args(r,out)")
	public void writeXml(JoinPoint thisJoinPoint, java.sql.ResultSet r, Writer out)
	{
		final ExceptionFactory ef = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ef != null) super.logLatency(HIGH, thisJoinPoint, ef);
	}

	@Before("call(* javax.sql.rowset.WebRowSet.writeXml(java.io.Writer)) && args(out)")
	public void writeXml(JoinPoint thisJoinPoint, Writer out)
	{
		final ExceptionFactory ef = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ef != null) super.logLatency(HIGH, thisJoinPoint, ef);
	}

}
