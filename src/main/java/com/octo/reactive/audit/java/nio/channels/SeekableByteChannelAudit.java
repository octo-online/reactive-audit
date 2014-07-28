package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 3
@Aspect
public class SeekableByteChannelAudit extends AbstractChannelsAudit
{
	@Before("call(* java.nio.channels.SeekableByteChannel.position(..))")
	public void position(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.nio.channels.SeekableByteChannel.truncate(long))")
	public void truncate(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
}
