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
public class RemoteObjectInvocationHandlerAudit extends NetworkAudit
{
	@Before("call(java.net.Socket java.lang.rmi.server.RemoteObjectInvocationHandler.invoke(String,int))")
	public void invoke(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
