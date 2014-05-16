package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Writer;

@Aspect
public class BufferedWriterAspect extends AbstractWriterAudit
{
	// FIXME : est-ce necessaire ?
	@Pointcut("call(* java.io.BufferedWriter.newLine())")
	public void newLine() { }

	@Before("(newLine())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(LatencyLevel.HIGH,thisJoinPoint,(Writer)thisJoinPoint.getTarget());
	}

}