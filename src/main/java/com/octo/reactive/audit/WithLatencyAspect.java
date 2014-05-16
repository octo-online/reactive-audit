package com.octo.reactive.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by pprados on 09/05/2014.
 */
@Aspect
public class WithLatencyAspect extends AbstractAudit
{
	@Before("execution(@com.octo.reactive.audit.WithLatency * *(..) )")
	public void beforeSuppress(JoinPoint thisJoinPoint)
			throws AuditReactiveException
	{
		latency(LatencyLevel.HIGH,thisJoinPoint);
	}
}
