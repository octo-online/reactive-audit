package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

public abstract class AbstractAudit
{
	protected final AuditReactive config = AuditReactive.config;

	private static boolean isReactiveThread()
	{
		return AuditReactive.config.isThreadNameMatch(Thread.currentThread().getName());
	}

	abstract protected AuditReactiveException newException(Latency latency, JoinPoint thisJoinPoint);

	private boolean checkForAll()
	{
		return isReactiveThread() && !config.isSuppressAudit();
	}

	protected void latency(Latency latency,
	                       JoinPoint thisJoinPoint
	) throws AuditReactiveException
	{
		logLatency(latency, thisJoinPoint, newException(latency, thisJoinPoint));
	}

	protected void logLatency(Latency latency,
	                          JoinPoint thisJoinPoint,
	                          AuditReactiveException e)
			throws AuditReactiveException
	{
		if (checkForAll())
		{
			final AuditReactive config = AuditReactive.config;
			if (!config.isAfterStartupDelay())
				return;
			config.logIfNew(latency, e);
			if (config.isThrow())  // LOW, MEDIUM, HIGH ?
				throw e;
		}
	}


}
