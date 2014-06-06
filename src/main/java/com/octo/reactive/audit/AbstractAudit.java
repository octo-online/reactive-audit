package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.aspectj.lang.JoinPoint;

/**
 * Created by pprados on 07/05/2014.
 */
public class AbstractAudit
{
	protected ConfigAuditReactive config = ConfigAuditReactive.config;

	protected static boolean isReactiveThread()
	{
		boolean reactiveThread = ConfigAuditReactive.config.isThreadNameMatch(Thread.currentThread().getName());
		return reactiveThread;
	}

	private boolean checkForAll()
	{
		return isReactiveThread() && !config.isSuppressAudit();
	}

	protected void latency(Latency latencyLevel, JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		if (checkForAll())
		{
			final ConfigAuditReactive config = ConfigAuditReactive.config;
			if (!config.isAfterStartupDelay())
				return;
			AuditReactiveException e = new FileAuditReactiveException(thisJoinPoint.getSignature().toString());
			config.logIfNew(latencyLevel, e);
			if (config.isThrow())  // LOW, MEDIUM, HIGH ?
				throw e;
		}
	}
}
