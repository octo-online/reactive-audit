package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.InputStream;

import static com.octo.reactive.audit.Latency.*;

@Aspect
public class InputStreamAspect extends AbstractInputStreamAudit
{
	@Pointcut("call(* java.io.InputStream.available())")
	public void available()
	{
	}

	@Pointcut("call(* java.io.InputStream.read(..))")
	public void read()
	{
	}

	@Pointcut("call(* java.io.InputStream.skip(long))")
	public void skip()
	{
	}

	@Pointcut("call(* java.io.InputStream.close())")
	public void close()
	{
	}

	@Before("(available() || skip())")
	public void advice_low(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint, (InputStream) thisJoinPoint.getTarget());
	}

	@Before("(close())")
	public void advice_medium(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint, (InputStream) thisJoinPoint.getTarget());
	}

	@Before("(read())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint, (InputStream) thisJoinPoint.getTarget());
	}
}
