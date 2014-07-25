package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AbstractDefaultAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 5
@Aspect
public class ThreadAudit extends AbstractDefaultAudit
{
	@Before("call(* java.lang.Thread.join(..))")
	public void join(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* java.lang.Thread.sleep(..))")
	public void sleep(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
