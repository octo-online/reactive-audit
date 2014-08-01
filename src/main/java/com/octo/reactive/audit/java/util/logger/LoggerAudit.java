package com.octo.reactive.audit.java.util.logger;

import com.octo.reactive.audit.AbstractCPUAudit;
import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

// Remove audit for logger
@Aspect
public class LoggerAudit extends AbstractCPUAudit
{
	@Before("execution(* java.util.logger.Logger.*(..))")
	public void beforeLogger(JoinPoint thisJoinPoint)
	{
		AuditReactive.config.incSuppress();
	}

	@After("execution(* java.util.logger.Logger.*(..))")
	public void afterLogger(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		AuditReactive.config.decSuppress();
	}
}
