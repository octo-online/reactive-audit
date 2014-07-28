package com.octo.reactive.audit.java.util.concurrent.locks;

import com.octo.reactive.audit.AbstractCPUAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 5
@Aspect
public class ConditionAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.concurrent.locks.Condition.await*(..))")
	public void await(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}
}
