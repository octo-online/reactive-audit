package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

@Aspect
public class InputStreamAspect extends AbstractInputStreamAudit
{
	@Before("call(* java.io.InputStream+.available())")
	public void available(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint, (InputStream) thisJoinPoint.getTarget());
	}

	@Before("call(* java.io.InputStream+.skip(long))")
	public void skip(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint, (InputStream) thisJoinPoint.getTarget());
	}

	@Before("call(* java.io.InputStream+.read(..))")
	public void read(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			config.logger.finest(
					thisJoinPoint.toLongString() + "  with " + thisJoinPoint.getTarget().getClass().getName());
		}
		latency(HIGH, thisJoinPoint, (InputStream) thisJoinPoint.getTarget());
	}

}
