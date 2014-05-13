package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.FileOutputStream;

@Aspect
public class BufferedWriterAspect extends AbstractWriterAudit
{
	@Pointcut("call(* java.io.BufferedWriter.newLine())")
	public void newLine(){ }

	@Before("initialization(java.io.BufferedWriter+.new(..))")
	@LatencyLevel(LatencyLevel.MEDIUM)
	public void new_(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		mediumLatency(thisJoinPoint);
	}

	@Before("(newLine())")
	@LatencyLevel(LatencyLevel.HIGH)
	public void advice_high(JoinPoint thisJoinPoint)
	{
		if (isLastOutputStreamFromWriterWithLatency(thisJoinPoint))
			highLatency(thisJoinPoint);
	}

}