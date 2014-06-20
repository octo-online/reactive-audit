package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 18/06/2014.
 */
@Aspect
public class FileChannelAudit extends AbstractChannelsAudit
{
	@Before("call(long java.nio.channels.FileChannel.transferFrom(java.nio.channels.ReadableByteChannel,long,long))")
	public void transferFrom(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(long java.nio.channels.FileChannel.transferTo(long,long,java.nio.channels.WritableByteChannel))")
	public void transferTo(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(void java.nio.channels.spi.AbstractInterruptibleChannel.begin())")
	public void begin(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
