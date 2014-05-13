package com.octo.reactive.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.SuppressAjWarnings;

/**
 * Created by pprados on 09/05/2014.
 */
@Aspect
public class SuppressAuditReactiveAspect
{
	@Before("execution(@com.octo.reactive.audit.SuppressAuditReactive * *(..) )")
	public void beforeSuppress(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		ConfigAuditReactive.config.incSuppress();
	}

	@After("execution(@com.octo.reactive.audit.SuppressAuditReactive * *(..))")
	public void afterSuppress(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		ConfigAuditReactive.config.decSuppress();
	}
}
