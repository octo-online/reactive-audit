package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 18/06/2014.
 */
@Aspect
public class SelectableChannelAudit extends AbstractChannelsAudit
{
	//	@Before("call(java.nio.channels.SelectableChannel java.nio.channels.spi.AbstractSelectableChannel.configureBlocking(boolean)) && args(blocking)")
	@Before("call(* java.nio.channels.SelectableChannel+.configureBlocking(boolean)) " +
			        "&& args(blocking)")
	public void configureBlocking(JoinPoint thisJoinPoint, boolean blocking)
	{
		if (blocking)
			latency(HIGH, thisJoinPoint);
	}
}
