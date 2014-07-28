package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.nio.channels.InterruptibleChannel;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 1
@Aspect
public class ChannelAudit extends AbstractChannelsAudit
{
	// FIXME : TU
	@Before("call(void java.nio.channels.Channel+.close())")
	public void close(JoinPoint thisJoinPoint)
	{
		if (thisJoinPoint.getTarget() instanceof InterruptibleChannel)
			return;
		latency(MEDIUM, thisJoinPoint);
	}

}
