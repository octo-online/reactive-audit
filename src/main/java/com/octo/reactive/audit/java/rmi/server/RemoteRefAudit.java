package com.octo.reactive.audit.java.rmi.server;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 2
@Aspect
public class RemoteRefAudit extends AbstractNetworkAudit
{
	@Before("call(* java.rmi.server.RemoteRef.invoke(..))")
	public void invoke(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
