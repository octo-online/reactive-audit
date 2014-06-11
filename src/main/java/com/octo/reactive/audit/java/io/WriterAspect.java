package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.Writer;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;

@Aspect
public class WriterAspect extends AbstractWriterAudit
{
	@Pointcut("call(* java.io.Writer.append(..))")
	public void append()
	{
	}

	@Pointcut("call(* java.io.Writer.close())")
	public void close()
	{
	}

	@Pointcut("call(* java.io.Writer.flush())")
	public void flush()
	{
	}

	@Pointcut("call(* java.io.Writer.write(..))")
	public void write()
	{
	}

	@Before("(append() || close())")
	public void advice_medium(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}

	@Before("(write() || flush())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}
}
