package com.octo.reactive.audit.javax.xml.soap;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 2
@Aspect
public class SOAPConnectionAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.xml.soap.SOAPConnection.call(javax.xml.soap.SOAPMessage,Object))")
	public void call(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* javax.xml.soap.SOAPConnection.get(Object))")
	public void get(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
