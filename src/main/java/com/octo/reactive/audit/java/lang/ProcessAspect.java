package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AbstractAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class ProcessAspect extends AbstractAudit
{
	@Before("call(* java.lang.Process.waitFor(..))")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
