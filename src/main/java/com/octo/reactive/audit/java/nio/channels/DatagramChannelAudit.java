package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.nio.channels.SelectableChannel;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 3
@Aspect
public class DatagramChannelAudit extends AbstractChannelsAudit
{
	@Before("call(java.nio.channels.DatagramChannel java.nio.channels.DatagramChannel.connect(java.net.SocketAddress))")
	public void connect(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(java.net.SocketAddress java.nio.channels.DatagramChannel.receive(java.nio.ByteBuffer))")
	public void receive(JoinPoint thisJoinPoint)
	{
		SelectableChannel asc = (SelectableChannel) thisJoinPoint.getTarget();
		if (asc.isBlocking())
			latency(HIGH, thisJoinPoint);
	}

	@Before("call(int java.nio.channels.DatagramChannel.send(java.nio.ByteBuffer,java.net.SocketAddress))")
	public void send(JoinPoint thisJoinPoint)
	{
		SelectableChannel asc = (SelectableChannel) thisJoinPoint.getTarget();
		if (asc.isBlocking())
			latency(HIGH, thisJoinPoint);
	}
}
