package com.octo.reactive.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MainAspect extends AbstractCPUAudit
{
	@Before("execution(public static void *.main(..))")
	public void startup(JoinPoint thisJoinPoint)
			throws Throwable
	{
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			@Override
			public void run()
			{
				AuditReactive.config.shutdown();
			}
		});
		AuditReactive.config.startup();
	}
}
