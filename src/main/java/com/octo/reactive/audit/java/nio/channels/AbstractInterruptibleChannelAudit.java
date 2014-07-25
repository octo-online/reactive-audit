package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods : 1
@Aspect
public class AbstractInterruptibleChannelAudit extends AbstractChannelsAudit
{
	@Before("call(void java.nio.channels.spi.AbstractInterruptibleChannel.begin())")
	public void begin(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}
}
