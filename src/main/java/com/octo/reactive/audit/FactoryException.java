package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.FileAuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.aspectj.lang.JoinPoint;

/**
 * Created by pprados on 27/06/2014.
 */
public class FactoryException
{
	public static NetworkAuditReactiveException newNetwork(Latency latency, JoinPoint thisJoinPoint)
	{
		return new NetworkAuditReactiveException(latency, thisJoinPoint.getSignature().toString());
	}

	public static FileAuditReactiveException newFile(Latency latency, JoinPoint thisJoinPoint)
	{
		return new FileAuditReactiveException(latency, thisJoinPoint.getSignature().toString());
	}
}
