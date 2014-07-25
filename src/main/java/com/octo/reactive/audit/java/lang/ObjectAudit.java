package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AbstractDefaultAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 3
@Aspect
public class ObjectAudit extends AbstractDefaultAudit
{
	@Before("call(* java.lang.Object.wait(..))")
	public void wait(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
