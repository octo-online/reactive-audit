package com.octo.reactive.audit.java.util.concurrent.locks;

import com.octo.reactive.audit.AbstractCPUAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 4
@Aspect
public class AbstractQueuedLongSynchronizerAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.concurrent.locks.AbstractQueuedLongSynchronizer.acquire*(..))")
	public void await(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}
}
