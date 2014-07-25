package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

import java.nio.channels.FileChannel;

abstract class AbstractChannelsAudit extends AbstractAudit
{
	protected void latency(Latency latency, JoinPoint thisJoinPoint)
	{
		AuditReactiveException ex = null;
		if (thisJoinPoint.getTarget() instanceof FileChannel)
			ex = FactoryException.newFile(latency, thisJoinPoint);
		else
			ex = FactoryException.newNetwork(latency, thisJoinPoint);
		super.latency(latency, thisJoinPoint, ex);
	}

	@Override
	protected AuditReactiveException newException(Latency latency, JoinPoint thisJoinPoint)
	{
		return FactoryException.newNetwork(latency, thisJoinPoint);
	}
}
