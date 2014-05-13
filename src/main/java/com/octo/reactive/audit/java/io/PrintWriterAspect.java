package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.*;

@Aspect
public class PrintWriterAspect extends AbstractWriterAudit
{
	// FIXME: new ?
	@Pointcut("call(* java.io.PrintWriter.format(..))")
    public void format(){ }
	@Pointcut("call(* java.io.PrintWriter.print(..))")
    public void print() { }
	@Pointcut("call(* java.io.PrintWriter.printf(..))")
	public void printf() { }
	@Pointcut("call(* java.io.PrintWriter.println(..))")
	public void println() { }

	@Before("initialization(java.io.PrintWriter+.new(..))")
	@LatencyLevel(LatencyLevel.MEDIUM)
	public void new_(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		mediumLatency(thisJoinPoint);
	}

	@Before("(format() || print() || printf() || println())")
	@LatencyLevel(LatencyLevel.HIGH)
	public void advice_high(JoinPoint thisJoinPoint)
	{
		if (isLastOutputStreamFromWriterWithLatency(thisJoinPoint))
			highLatency(thisJoinPoint);
	}
}