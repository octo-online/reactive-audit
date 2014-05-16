package com.octo.reactive.audit;

import org.aspectj.lang.JoinPoint;

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
    protected void latency(int level,JoinPoint thisJoinPoint) throws AuditReactiveException
    {
	    if (checkForAll())
	    {
		    // FIXME: level
		    ConfigAuditReactive.config.info(thisJoinPoint.getSignature());
		    if (config.isThrow())  // LOW, MEDIUM, HIGH ?
			    throw new FileAuditReactiveException(thisJoinPoint.getSignature().toString());
	    }
    }
}
