package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 18/06/2014.
 */
@Aspect
public class GatheringByteChannelAudit extends AbstractChannelsAudit
{
	@Before("call(long java.nio.channels.GatheringByteChannel.write(java.nio.ByteBuffer[],..))")
//	@Before("call(long java.nio.channels.GatheringByteChannel.write(..)) && target(java.nio.channels.GatheringByteChannel)")
	public void write(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
