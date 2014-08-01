package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.URLTools;
import com.octo.reactive.audit.java.io.AbstractInputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractOutputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractReaderAudit;
import com.octo.reactive.audit.java.io.AbstractWriterAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
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
		AuditReactiveException ex = AbstractOutputStreamAudit.latencyOutputStream(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.marshal(Object,java.io.Writer)) && args(o,out)")
	public void marshal(JoinPoint thisJoinPoint, Object o, Writer out)
	{
		AuditReactiveException ex = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.marshal(Object,java.net.URL)) && args(o,url)")
	public void marshal(JoinPoint thisJoinPoint, Object o, URL url)
	{
		AuditReactiveException ex = URLTools.latencyURL(config, thisJoinPoint, url);
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
		AuditReactiveException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.unmarshal(java.io.Reader,Class)) && args(in,cl)")
	public void unmarshal(JoinPoint thisJoinPoint, Reader in, Class<?> cl)
	{
		AuditReactiveException ex = AbstractReaderAudit.latencyReader(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.unmarshal(java.net.URL,Class)) && args(url,cl)")
	public void unmarshal(JoinPoint thisJoinPoint, URL url, Class<?> cl)
	{
		AuditReactiveException ex = URLTools.latencyURL(config, thisJoinPoint, url);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}
	// TODO: XMLEventReader et XMLStreamReader ?
	// TODO: XMLEventWriter et XMLStreamWriter ?
}
