package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

import java.nio.channels.FileChannel;

class AbstractChannelsAudit extends AbstractAudit
{
	protected void latency(Latency level, JoinPoint thisJoinPoint)
	{
		AuditReactiveException ex = null;
		if (thisJoinPoint.getTarget() instanceof FileChannel)
			ex = FactoryException.newFile(thisJoinPoint);
		else
			ex = FactoryException.newNetwork(thisJoinPoint);
		super.latency(level, thisJoinPoint, ex);
	}

	@Override
	protected AuditReactiveException newException(JoinPoint thisJoinPoint)
	{
		return FactoryException.newNetwork(thisJoinPoint);
	}
}
