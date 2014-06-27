package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.OutputStream;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class OutputStreamAspect extends AbstractOutputStreamAudit
{
	@Before("call(* java.io.OutputStream.write(..))")
	public void write(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint, (OutputStream) thisJoinPoint.getTarget());
	}

}
