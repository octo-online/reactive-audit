package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.DefaultAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 18/06/2014.
 */
@Aspect
public class AsynchronousChannelGroupAudit extends DefaultAudit
{
	@Before("call(boolean java.nio.channels.AsynchronousChannelGroup.awaitTermination(long,java.util.concurrent.TimeUnit))")
	public void awaitTermination(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
