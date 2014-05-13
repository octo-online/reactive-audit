package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.SuppressAjWarnings;

@Aspect
public class FilterWriterAspect extends AbstractWriterAudit
{
	// FIXME: il faut capturer le super, pas le new direct
	@Before("initialization(java.io.FilterWriter+.new(..))")
	@LatencyLevel(LatencyLevel.MEDIUM)
	public void new_(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		System.err.println("JE SUIS LA");
		if (isLastOutputStreamFromWriterWithLatency(thisJoinPoint))
			mediumLatency(thisJoinPoint);
	}

}