package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.Writer;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 1
@Aspect
public class BufferedWriterAudit extends AbstractWriterAudit
{
	// FIXME : est-ce necessaire ?
	@Before("call(* java.io.BufferedWriter.newLine())")
	public void newLine(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}

}
