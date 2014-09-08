package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.WithLatency;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class WithLatencyAudit extends AbstractCPUAudit
{
	@Before("execution(@com.octo.reactive.audit.lib.WithLatency * *(..) )")
	public void with(JoinPoint thisJoinPoint)
			throws AuditReactiveException
	{
		WithLatency withLatency = ((MethodSignature) thisJoinPoint.getSignature()).getMethod().getAnnotation(
				WithLatency.class);
		latency(withLatency.value(), thisJoinPoint);
	}
}
