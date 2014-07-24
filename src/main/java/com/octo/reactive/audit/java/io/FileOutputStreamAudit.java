package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.LOW;

@Aspect
public class FileOutputStreamAudit extends AbstractOutputStreamAudit
{
	@Before("call(java.io.FileOutputStream+.new(..))")
	public void new_(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
}
