package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

abstract public class AbstractCPUAudit extends AbstractAudit
{
	@Override
	protected AuditReactiveException newException(Latency latency, JoinPoint thisJoinPoint)
	{
		return new CPUAuditReactiveException(latency, thisJoinPoint.getSignature().toString());
	}
}
