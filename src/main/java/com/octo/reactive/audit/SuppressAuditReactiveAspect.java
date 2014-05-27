package com.octo.reactive.audit;

import com.octo.reactive.audit.annotation.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by pprados on 09/05/2014.
 */
@Aspect
public class SuppressAuditReactiveAspect
{
	@Before("execution(@com.octo.reactive.audit.annotation.SuppressAuditReactive * *(..) )")
	public void beforeSuppress(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		ConfigAuditReactive.config.incSuppress();
	}

	@After("execution(@com.octo.reactive.audit.annotation.SuppressAuditReactive * *(..))")
	public void afterSuppress(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		ConfigAuditReactive.config.decSuppress();
	}
}
