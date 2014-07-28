package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AbstractCPUAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 1
@Aspect
public class ForkJoinPoolManagedBlockerAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.concurrent.ForkJoinPool.ManagedBlocker.block() )")
	public void block(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
