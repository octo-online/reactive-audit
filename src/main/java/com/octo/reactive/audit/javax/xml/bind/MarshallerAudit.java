package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.FileAudit;
import com.octo.reactive.audit.java.io.AbstractOutputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractWriterAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.OutputStream;
import java.io.Writer;
import java.sql.ResultSet;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class MarshallerAudit extends FileAudit
{
	@Before("call(* javax.xml.bind.Marshaller.marshal(Object,java.io.File output))")
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

	@Before("call(* javax.xml.bind.Marshaller.marshal(Object,java.io.Writer)) && args(r,out)")
	public void marshal(JoinPoint thisJoinPoint, ResultSet rs, Writer out)
	{
		AuditReactiveException ex = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}
	// TODO: XMLEventWriter et XMLStreamWriter ?
}
