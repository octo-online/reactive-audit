package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.Reader;

import static com.octo.reactive.audit.lib.Latency.LOW;

@Aspect
public class ReadableAudit extends AbstractReaderAudit
{
	@Before("call(* java.io.Reader+.skip(..))")
	public void skip(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint, (Reader) thisJoinPoint.getTarget());
	}

}
