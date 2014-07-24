package com.octo.reactive.audit.java.rmi.server;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class RMIClientSocketFactoryAudit extends NetworkAudit
{
	@Before("call(java.net.Socket java.lang.rmi.server.RMIClientSocketFactory.createSocket(String,int))")
	public void createSocket(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
