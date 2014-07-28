package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AbstractCPUAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 1
@Aspect
public class AsynchronousChannelGroupAudit extends AbstractCPUAudit
{
	@Before("call(boolean java.nio.channels.AsynchronousChannelGroup.awaitTermination(long,java.util.concurrent.TimeUnit))")
	public void awaitTermination(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
