package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import static com.octo.reactive.audit.Latency.HIGH;

@Aspect
public class InputStreamReaderAspect extends AbstractReaderAudit
{
	// FIXME: c'est necessaire ?
	@Pointcut("call(* java.io.InputStreamReader.readLine())")
	public void readLine()
	{
	}

	@Before("(readLine())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
