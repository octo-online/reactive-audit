package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.Writer;

import static com.octo.reactive.audit.Latency.HIGH;

@Aspect
public class BufferedWriterAspect extends AbstractWriterAudit
{
	// FIXME : est-ce necessaire ?
	@Pointcut("call(* java.io.BufferedWriter.newLine())")
	public void newLine() { }

	@Before("(newLine())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}

}
