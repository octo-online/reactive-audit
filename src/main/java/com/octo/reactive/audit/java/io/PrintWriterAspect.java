package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import static com.octo.reactive.audit.Latency.HIGH;

@Aspect
public class PrintWriterAspect extends AbstractWriterAudit
{
	@Pointcut("call(* java.io.PrintWriter.format(..))")
	public void format()
	{
	}

	@Pointcut("call(* java.io.PrintWriter.print(..))")
	public void print()
	{
	}

	@Pointcut("call(* java.io.PrintWriter.printf(..))")
	public void printf()
	{
	}

	@Pointcut("call(* java.io.PrintWriter.println(..))")
	public void println()
	{
	}

	@Before("(format() || print() || printf() || println())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
