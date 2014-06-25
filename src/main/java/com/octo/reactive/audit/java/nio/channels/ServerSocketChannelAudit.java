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
public class ServerSocketChannelAudit extends AbstractChannelsAudit
{
	@Before("call(java.nio.channels.SocketChannel java.nio.channels.ServerSocketChannel.accept())")
	public void accept(JoinPoint thisJoinPoint)
	{
		SelectableChannel asc = (SelectableChannel) thisJoinPoint.getTarget();
		if (asc.isBlocking())
			latency(HIGH, thisJoinPoint);
	}
}
