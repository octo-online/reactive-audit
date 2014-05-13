package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.io.*;

@Aspect
public class InputStreamReaderAspect extends AbstractReaderAudit
{
	@Before("initialization(java.io.InputStreamReader+.new(..))")
	@LatencyLevel(LatencyLevel.MEDIUM)
	public void new_(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		mediumLatency(thisJoinPoint);
	}


	@Pointcut("call(* java.io.InputStreamReader.readLine())")
	public void readLine(){ }

	@Before("(readLine())")
	@LatencyLevel(LatencyLevel.HIGH)
	public void advice_high(JoinPoint thisJoinPoint)
	{
		if (isLastInputStreamInReaderWithLatency(thisJoinPoint))
			highLatency(thisJoinPoint);
	}
}