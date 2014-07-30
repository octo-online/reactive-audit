package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SuppressAuditReactiveAudit
{
	@Before("execution(@com.octo.reactive.audit.lib.SuppressAuditReactive * *(..) )")
	public void beforeSuppress(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		AuditReactive.config.incSuppress();
	}

	@After("execution(@com.octo.reactive.audit.lib.SuppressAuditReactive * *(..))")
	public void afterSuppress(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		AuditReactive.config.decSuppress();
	}
}
