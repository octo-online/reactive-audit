package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.FileAudit;
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
import java.sql.ResultSet;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class JAXBAudit extends FileAudit
{
	@Before("call(* javax.xml.bind.JAXB.marshal(Object,java.io.File output))")
	public void marshal(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* javax.xml.bind.Marshaller.marshal(Object,java.io.OutputStream)) && args(out)")
	public void marshal(JoinPoint thisJoinPoint, OutputStream out)
	{
		AuditReactiveException ex = AbstractOutputStreamAudit.latencyOutputStream(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.marshal(Object,java.io.Writer)) && args(r,out)")
	public void marshal(JoinPoint thisJoinPoint, ResultSet rs, Writer out)
	{
		AuditReactiveException ex = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.marshal(Object,java.net.URL)) && args(r,url)")
	public void marshal(JoinPoint thisJoinPoint, ResultSet rs, URL url)
	{
		AuditReactiveException ex = URLTools.latencyURL(config, thisJoinPoint, url);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.unmarshal(java.io.File output,..))")
	public void unmarshal(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* javax.xml.bind.JAXB.unmarshal(java.io.InputStream,..)) && args(in)")
	public void unmarshal(JoinPoint thisJoinPoint, InputStream in)
	{
		AuditReactiveException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.unmarshal(java.io.Reader,..)) && args(in)")
	public void unmarshal(JoinPoint thisJoinPoint, Reader in)
	{
		AuditReactiveException ex = AbstractReaderAudit.latencyReader(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.JAXB.unmarshal(java.net.URL,..)) && args(url)")
	public void unmarshal(JoinPoint thisJoinPoint, URL url)
	{
		AuditReactiveException ex = URLTools.latencyURL(config, thisJoinPoint, url);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}
	// TODO: XMLEventReader et XMLStreamReader ?
	// TODO: XMLEventWriter et XMLStreamWriter ?
}
