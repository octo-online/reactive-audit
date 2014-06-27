package com.octo.reactive.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by pprados on 09/05/2014.
 */
@Aspect
public class MainAspect extends DefaultAudit
{
	@Around("execution(public static void *.main(..))")
	public void startup(ProceedingJoinPoint thisJoinPoint)
			throws Throwable
	{
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			@Override
			public void run()
			{
				ConfigAuditReactive.config.shutdown();
			}
		});
		ConfigAuditReactive.config.startup();
		thisJoinPoint.proceed();
	}
}
