package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.Reader;

import static com.octo.reactive.audit.Latency.*;

@Aspect
public class ReaderAspect extends AbstractReaderAudit
{
	@Pointcut("call(* java.io.Reader.read(..))")
	public void read()
	{
	}

	@Pointcut("call(* java.io.Reader.skip(..))")
	public void skip()
	{
	}

	@Pointcut("call(* java.io.Reader.close())")
	public void close()
	{
	}

	@Before("(skip())")
	public void advice_low(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint, (Reader) thisJoinPoint.getTarget());
	}

	@Before("(close())")
	public void advice_medium(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint, (Reader) thisJoinPoint.getTarget());
	}

	@Before("(read())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint, (Reader) thisJoinPoint.getTarget());
	}
}
