package com.octo.reactive.audit.java.rmi.registry;

import com.octo.reactive.audit.DefaultAudit;
import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class RegistryAudit extends NetworkAudit
{
	@Before("call(java.rmi.Remote java.lang.rmi.registry.Registry.lookup(String))")
	public void lookup(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
