package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.LOW;

/**
 * Created by pprados on 18/06/2014.
 */
@Aspect
public class SeekableByteChannelAudit extends AbstractChannelsAudit
{
	@Before("call(* java.nio.channels.SeekableByteChannel.position(long))")
	public void position(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.nio.channels.SeekableByteChannel.truncate(long))")
	public void truncate(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
//	@Before("call(* java.nio.channels.SeekableByteChannel.read(java.nio.ByteBuffer))")
//	public void read(JoinPoint thisJoinPoint)
//	{
//		latency(HIGH, thisJoinPoint);
//	}
//	@Before("call(* java.nio.channels.SeekableByteChannel.write(java.nio.ByteBuffer))")
//	public void write(JoinPoint thisJoinPoint)
//	{
//		latency(HIGH, thisJoinPoint);
//	}
}
