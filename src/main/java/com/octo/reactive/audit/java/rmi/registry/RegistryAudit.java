package com.octo.reactive.audit.java.rmi.registry;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 1
@Aspect
public class RegistryAudit extends AbstractNetworkAudit
{
	@Before("call(java.rmi.Remote java.rmi.registry.Registry.lookup(String))")
	public void lookup(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
