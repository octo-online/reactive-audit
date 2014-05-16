package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.io.*;

@Aspect
public class InputStreamReaderAspect extends AbstractReaderAudit
{
	// FIXME: c'est necessaire ?
	@Pointcut("call(* java.io.InputStreamReader.readLine())")
	public void readLine(){ }

	@Before("(readLine())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(LatencyLevel.HIGH,thisJoinPoint);
	}
}