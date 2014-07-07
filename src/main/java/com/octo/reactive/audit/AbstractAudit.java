package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

/**
 * Created by pprados on 07/05/2014.
 */
public abstract class AbstractAudit
{
	protected ConfigAuditReactive config = ConfigAuditReactive.config;

	protected static boolean isReactiveThread()
	{
		return ConfigAuditReactive.config.isThreadNameMatch(Thread.currentThread().getName());
	}

	abstract protected AuditReactiveException newException(JoinPoint thisJoinPoint);

	private boolean checkForAll()
	{
		return isReactiveThread() && !config.isSuppressAudit();
	}

	protected void latency(Latency latencyLevel,
	                       JoinPoint thisJoinPoint
	) throws AuditReactiveException
	{
		latency(latencyLevel, thisJoinPoint, newException(thisJoinPoint));
	}

	protected void latency(Latency latencyLevel,
	                       JoinPoint thisJoinPoint, AuditReactiveException e) throws AuditReactiveException
	{
		if (checkForAll())
		{
			final ConfigAuditReactive config = ConfigAuditReactive.config;
			if (!config.isAfterStartupDelay())
				return;
			config.logIfNew(latencyLevel, e);
			if (config.isThrow())  // LOW, MEDIUM, HIGH ?
				throw e;
		}
	}


}
