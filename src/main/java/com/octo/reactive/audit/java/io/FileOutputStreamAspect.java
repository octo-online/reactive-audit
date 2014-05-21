package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.Latency.LOW;

@Aspect
public class FileOutputStreamAspect extends AbstractOutputStreamAudit
{
	@Before("call(java.io.FileOutputStream+.new(..))")
	public void new_(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
}
