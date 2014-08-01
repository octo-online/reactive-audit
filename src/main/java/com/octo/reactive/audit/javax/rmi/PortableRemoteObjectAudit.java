package com.octo.reactive.audit.javax.rmi;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 1
@Aspect
public class PortableRemoteObjectAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.rmi.PortableRemoteObject.connect(..))")
	public void connect(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
