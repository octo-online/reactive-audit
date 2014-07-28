package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AbstractCPUAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 4
@Aspect
public class PhaserAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.concurrent.Phaser.arriveAndAwaitAdvance() )")
	public void arriveAndAwaitAdvance(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(* java.util.concurrent.Phaser.awaitAdvance(..))")
	public void awaitAdvance(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(* java.util.concurrent.Phaser.awaitAdvanceInterruptibly(..) )")
	public void awaitAdvanceInterruptibly(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}
}
