package com.octo.reactive.audit.javax.net;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 5
@Aspect
public class SocketFactoryAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.net.SocketFactory.createSocket(..))")
	public void createSocket(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
