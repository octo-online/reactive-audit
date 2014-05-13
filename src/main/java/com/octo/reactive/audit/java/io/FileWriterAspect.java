package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class FileWriterAspect extends AbstractWriterAudit
{
	@Before("initialization(java.io.FileWriter+.new(..))")
	@LatencyLevel(LatencyLevel.MEDIUM)
	public void new_(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		if (isLastOutputStreamFromWriterWithLatency(thisJoinPoint))
			mediumLatency(thisJoinPoint);
	}

}