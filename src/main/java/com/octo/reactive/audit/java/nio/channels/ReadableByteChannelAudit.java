package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.nio.channels.SelectableChannel;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 18/06/2014.
 */
@Aspect
public class ReadableByteChannelAudit extends AbstractChannelsAudit
{
	@Before("call(int java.nio.channels.ReadableByteChannel+.read(java.nio.ByteBuffer))")
	public void read(JoinPoint thisJoinPoint)
	{
		if (thisJoinPoint.getTarget() instanceof SelectableChannel)
		{
			if (!((SelectableChannel) thisJoinPoint.getTarget()).isBlocking())
				return;
		}
		latency(HIGH, thisJoinPoint);
	}

}
