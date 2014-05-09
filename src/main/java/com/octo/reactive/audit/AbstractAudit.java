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
    protected void lowLatency(JoinPoint thisJoinPoint) throws AuditReactiveException
    {
	    if (checkForAll())
	    {
		    ConfigAuditReactive.logger.info(thisJoinPoint.getSignature());
		    if (config.isThrow())  // LOW, MEDIUM, HIGH ?
			    throw new FileAuditReactiveException(thisJoinPoint.getSignature().toString());
	    }
    }
    protected void mediumLatency(JoinPoint thisJoinPoint) throws AuditReactiveException
    {
	    if (checkForAll())
	    {
		    System.err.println(thisJoinPoint.getSignature());
		    ConfigAuditReactive.logger.warn(thisJoinPoint.getSignature());
		    if (config.isThrow())
			    throw new FileAuditReactiveException(thisJoinPoint.getSignature().toString());
	    }
    }
    protected void highLatency(JoinPoint thisJoinPoint) throws AuditReactiveException
    {
	    if (checkForAll())
	    {
		    ConfigAuditReactive.logger.error(thisJoinPoint.getSignature());
		    if (config.isThrow())
			    throw new FileAuditReactiveException(thisJoinPoint.getSignature().toString());
	    }
    }
}
