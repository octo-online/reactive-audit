package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class WithLatencyAudit extends AbstractCPUAudit
{
	@Before("execution(@com.octo.reactive.audit.lib.WithLatency * *(..) )")
	public void beforeSuppress(JoinPoint thisJoinPoint)
			throws AuditReactiveException
	{
		latency(HIGH, thisJoinPoint);
	}
}
