package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AbstractDefaultAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 2
@Aspect
public class ProcessAudit extends AbstractDefaultAudit
{
	@Before("call(* java.lang.Process.waitFor(..))")
	public void waitFor(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
