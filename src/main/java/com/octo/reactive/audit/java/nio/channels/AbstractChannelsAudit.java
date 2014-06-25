package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.aspectj.lang.JoinPoint;

import java.nio.channels.FileChannel;

class AbstractChannelsAudit extends AbstractAudit
{
	protected void latency(Latency level, JoinPoint thisJoinPoint)
	{
		if (thisJoinPoint.getTarget() instanceof FileChannel)
			super.latency(level, thisJoinPoint,
			              new FileAuditReactiveException(thisJoinPoint.getSignature().toString()));
		else
			super.latency(level, thisJoinPoint);
	}

	@Override
	protected AuditReactiveException newException(JoinPoint thisJoinPoint)
	{
		return new NetworkAuditReactiveException(thisJoinPoint.getSignature().toString());
	}
}
