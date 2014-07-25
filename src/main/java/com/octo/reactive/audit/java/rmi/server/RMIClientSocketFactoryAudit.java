package com.octo.reactive.audit.java.rmi.server;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 1
@Aspect
public class RMIClientSocketFactoryAudit extends AbstractNetworkAudit
{
	@Before("call(java.net.Socket java.rmi.server.RMIClientSocketFactory.createSocket(String,int))")
	public void createSocket(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
