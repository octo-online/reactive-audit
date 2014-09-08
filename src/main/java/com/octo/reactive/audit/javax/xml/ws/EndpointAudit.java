package com.octo.reactive.audit.javax.xml.ws;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 5
@Aspect
public class EndpointAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.xml.ws.Endpoint.publish(..))")
	public void publish(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
