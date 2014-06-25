package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;

/**
 * Created by pprados on 18/06/2014.
 */
@Aspect
public class FileChannelAudit extends AbstractChannelsAudit
{
	@Before("call(java.nio.channels.FileChannel java.nio.channels.FileChannel.open(..))")
	public void open(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint, new FileAuditReactiveException(thisJoinPoint.getSignature().toString()));
	}

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

	@Before("call(java.nio.channels.FileLock java.nio.channels.FileChannel.lock(..))")
	public void lock(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
