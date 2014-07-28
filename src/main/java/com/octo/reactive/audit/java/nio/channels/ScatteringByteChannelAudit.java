package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 2
@Aspect
public class ScatteringByteChannelAudit extends AbstractChannelsAudit
{
	@Before("call(long java.nio.channels.ScatteringByteChannel+.read(..))")
	public void read(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
