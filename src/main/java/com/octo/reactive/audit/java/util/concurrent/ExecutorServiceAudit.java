package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AbstractCPUAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 3
@Aspect
public class ExecutorServiceAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.concurrent.ExecutorService.awaitTermination(long,java.util.concurrent.TimeUnit) )")
	public void awaitTermination(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(* java.util.concurrent.ExecutorService.invokeAny(java.util.Collection,long,java.util.concurrent.TimeUnit) )")
	public void invokeAny(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(* java.util.concurrent.ExecutorService.shutdownNow() )")
	public void shutdownNow(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
