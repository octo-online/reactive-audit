package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.FileAuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.aspectj.lang.JoinPoint;

public final class FactoryException
{
	private FactoryException()
	{
	}

	public static NetworkAuditReactiveException newNetwork(Latency latency, JoinPoint thisJoinPoint)
	{
		return newNetwork(latency, thisJoinPoint, null);
	}

	public static NetworkAuditReactiveException newNetwork(Latency latency, JoinPoint thisJoinPoint, CharSequence msg)
	{
		return new NetworkAuditReactiveException(latency,
		                                         (msg == null)
		                                         ? thisJoinPoint.getSignature().toString()
		                                         : thisJoinPoint.getSignature().toString() + " " + msg);
	}

	public static FileAuditReactiveException newFile(Latency latency, JoinPoint thisJoinPoint)
	{
		return newFile(latency, thisJoinPoint, null);
	}

	public static FileAuditReactiveException newFile(Latency latency, JoinPoint thisJoinPoint, CharSequence msg)
	{
		return new FileAuditReactiveException(latency, (msg == null)
		                                               ? thisJoinPoint.getSignature().toString()
		                                               : thisJoinPoint.getSignature().toString() + " " + msg);
	}
}
