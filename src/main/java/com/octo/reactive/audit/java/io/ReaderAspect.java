package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.Reader;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

@Aspect
public class ReaderAspect extends AbstractReaderAudit
{
	@Before("call(* java.io.Reader+.read(..))")
	public void read(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + thisJoinPoint.getTarget().getClass().getName());
		}
		latency(HIGH, thisJoinPoint, (Reader) thisJoinPoint.getTarget());
	}

	@Before("call(* java.io.Reader+.skip(..))")
	public void skip(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint, (Reader) thisJoinPoint.getTarget());
	}

}
