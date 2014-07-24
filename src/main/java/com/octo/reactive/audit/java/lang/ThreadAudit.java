package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.DefaultAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */

@Aspect
public class ThreadAudit extends DefaultAudit
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
