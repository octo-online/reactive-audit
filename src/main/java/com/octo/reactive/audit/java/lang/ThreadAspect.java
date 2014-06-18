package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.DefaultAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */

@Aspect
public class ThreadAspect extends DefaultAudit
{
	@Pointcut("call(* java.lang.Thread.join(..))")
	public void join()
	{
	}

	@Pointcut("call(* java.lang.Thread.sleep(..))")
	public void sleep()
	{
	}

	@Before("(join() || sleep())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
