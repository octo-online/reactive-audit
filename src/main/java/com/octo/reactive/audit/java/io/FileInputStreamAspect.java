package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.annotation.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.Latency.LOW;

@Aspect
public class FileInputStreamAspect extends AbstractInputStreamAudit
{
	@Before("call(java.io.FileInputStream+.new(..))")
	public void new_(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		latency(LOW, thisJoinPoint);
	}
}
