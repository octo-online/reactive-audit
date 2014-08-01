package com.octo.reactive.audit.javax.rmi.ssl;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 1
@Aspect
public class SslRMIClientSocketFactoryAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.rmi.ssl.SslRMIClientSocketFactory.createSocket(..))")
	public void createSocket(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
