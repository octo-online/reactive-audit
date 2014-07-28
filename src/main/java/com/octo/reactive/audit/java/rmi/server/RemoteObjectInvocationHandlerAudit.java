package com.octo.reactive.audit.java.rmi.server;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 1
@Aspect
public class RemoteObjectInvocationHandlerAudit extends AbstractNetworkAudit
{
	@Before("call(Object java.rmi.server.RemoteObjectInvocationHandler.invoke(..))")
	public void invoke(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
