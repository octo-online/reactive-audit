package com.octo.reactive.audit.java.rmi.server;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods : 2
@Aspect
public class RMISocketFactoryAudit extends AbstractNetworkAudit
{
	@Before("call(java.net.Socket java.rmi.server.RMISocketFactory.createServerSocket(int))")
	public void createServerSocket(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(java.net.Socket java.rmi.server.RMISocketFactory.createSocket(String,int))")
	public void createSocket(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
