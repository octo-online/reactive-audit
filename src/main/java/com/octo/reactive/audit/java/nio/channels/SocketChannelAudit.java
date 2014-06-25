package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.nio.channels.SocketChannel;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

/**
 * Created by pprados on 18/06/2014.
 */
@Aspect
public class SocketChannelAudit extends AbstractChannelsAudit
{
	@Before("call(boolean java.nio.channels.SocketChannel.connect(java.net.SocketAddress))")
	public void connect(JoinPoint thisJoinPoint)
	{
		if (((SocketChannel) thisJoinPoint.getTarget()).isBlocking())
			latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(java.nio.channels.SocketChannel java.nio.channels.SocketChannel.open(java.net.SocketAddress))")
	public void open(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(boolean java.nio.channels.SocketChannel.finishConnect())")
	public void finishConnect(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}
}
