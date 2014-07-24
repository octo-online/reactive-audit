package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class InputStreamReaderAudit extends AbstractReaderAudit
{
	// FIXME: c'est necessaire ?
	@Before("call(* java.io.InputStreamReader.readLine())")
	public void readLine(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
