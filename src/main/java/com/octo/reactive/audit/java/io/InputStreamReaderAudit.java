package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 2
@Aspect
public class InputStreamReaderAudit extends AbstractReaderAudit
{
	@Before("call(* java.io.InputStreamReader.read(..))")
	public void read(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
