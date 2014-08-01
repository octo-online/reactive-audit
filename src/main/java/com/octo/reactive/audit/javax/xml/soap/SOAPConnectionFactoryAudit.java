package com.octo.reactive.audit.javax.xml.soap;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 1
@Aspect
public class SOAPConnectionFactoryAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.xml.soap.SOAPConnectionFactory.createConnection())")
	public void createConnection(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}
}
