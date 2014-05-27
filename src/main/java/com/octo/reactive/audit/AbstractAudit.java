package com.octo.reactive.audit;

import com.octo.reactive.audit.annotation.AuditReactiveException;
import com.octo.reactive.audit.annotation.FileAuditReactiveException;
import org.aspectj.lang.JoinPoint;

import static com.octo.reactive.audit.Logger.Level.*;

/**
 * Created by pprados on 07/05/2014.
 */
public class AbstractAudit
{
	protected ConfigAuditReactive config=ConfigAuditReactive.config;

	protected static boolean isReactiveThread()
	{
		boolean reactiveThread=ConfigAuditReactive.config.isThreadNameMatch(Thread.currentThread().getName());
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
		    final ConfigAuditReactive config=ConfigAuditReactive.config;
		    if (!config.isAfterStartupDelay())
			    return;
		    Logger.Level level;
		    switch (latencyLevel)
		    {
			    case LOW:
				    level= INFO;
				case MEDIUM:
					level= WARN;
				default:
					level= ERROR;
		    }
		    config.logIfNew(level, thisJoinPoint.getSignature().toShortString());
		    if (config.isThrow())  // LOW, MEDIUM, HIGH ?
			    throw new FileAuditReactiveException(thisJoinPoint.getSignature().toString());
	    }
    }
}
