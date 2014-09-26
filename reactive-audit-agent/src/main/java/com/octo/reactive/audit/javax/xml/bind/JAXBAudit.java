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
import com.octo.reactive.audit.URLTools;
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
import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 8
@Aspect
public class JAXBAudit extends AbstractFileAudit
{
	@Before("call(* javax.xml.bind.JAXB.marshal(Object,java.io.File))")
	public void marshal(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* javax.xml.bind.JAXB.marshal(Object,java.io.OutputStream)) && args(o,out)")
	public void marshal(JoinPoint thisJoinPoint, Object o, OutputStream out)
	{
		ReactiveAuditException ex = AbstractOutputStreamAudit.latencyOutputStream(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.marshal(Object,java.io.Writer)) && args(o,out)")
	public void marshal(JoinPoint thisJoinPoint, Object o, Writer out)
	{
		ReactiveAuditException ex = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.marshal(Object,java.net.URL)) && args(o,url)")
	public void marshal(JoinPoint thisJoinPoint, Object o, URL url)
	{
		ReactiveAuditException ex = URLTools.latencyURL(config, thisJoinPoint, url);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.unmarshal(java.io.File,..))")
	public void unmarshal(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* javax.xml.bind.JAXB.unmarshal(java.io.InputStream,Class)) && args(in,cl)")
	public void unmarshal(JoinPoint thisJoinPoint, InputStream in, Class<?> cl)
	{
		ReactiveAuditException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.unmarshal(java.io.Reader,Class)) && args(in,cl)")
	public void unmarshal(JoinPoint thisJoinPoint, Reader in, Class<?> cl)
	{
		ReactiveAuditException ex = AbstractReaderAudit.latencyReader(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.unmarshal(java.net.URL,Class)) && args(url,cl)")
	public void unmarshal(JoinPoint thisJoinPoint, URL url, Class<?> cl)
	{
		ReactiveAuditException ex = URLTools.latencyURL(config, thisJoinPoint, url);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}
	// TODO: XMLEventReader et XMLStreamReader ?
	// TODO: XMLEventWriter et XMLStreamWriter ?
}
