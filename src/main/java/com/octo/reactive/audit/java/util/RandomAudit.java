package com.octo.reactive.audit.java.util;

import com.octo.reactive.audit.AbstractCPUAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 4
@Aspect
public class RandomAudit extends AbstractCPUAudit
{
	@Before("call(* java.util.Random.*(..) )")
	public void random(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

}
