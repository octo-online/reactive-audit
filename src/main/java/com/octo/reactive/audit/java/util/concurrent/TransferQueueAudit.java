package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AbstractCPUAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 2
@Aspect
public class TransferQueueAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.concurrent.TransferQueue.transfer(Object) )")
	public void transfer(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(* java.util.concurrent.TransferQueue.tryTransfer(Object,long,java.util.concurrent.TimeUnit) )")
	public void tryTransfer(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}
}
