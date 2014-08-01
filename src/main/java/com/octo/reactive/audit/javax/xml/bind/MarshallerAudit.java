package com.octo.reactive.audit.javax.xml.bind;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.java.io.AbstractOutputStreamAudit;
import com.octo.reactive.audit.java.io.AbstractWriterAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
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
		AuditReactiveException ex = AbstractOutputStreamAudit.latencyOutputStream(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.bind.Marshaller.marshal(Object,java.io.Writer)) && args(o,out)")
	public void marshal(JoinPoint thisJoinPoint, Object o, Writer out)
	{
		AuditReactiveException ex = AbstractWriterAudit.latencyWriter(config, HIGH, thisJoinPoint, out);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}
}
