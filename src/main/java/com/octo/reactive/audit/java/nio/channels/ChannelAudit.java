package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.annotation.Aspect;

/**
 * Created by pprados on 18/06/2014.
 */
@Aspect
public class ChannelAudit extends AbstractChannelsAudit
{
// FIXME
//	@Before("call(void java.nio.channels.Channel+.close())")
//	public void close(JoinPoint thisJoinPoint)
//	{
//		if (thisJoinPoint.getTarget() instanceof InterruptibleChannel)
//			return;
//		latency(MEDIUM, thisJoinPoint);
//	}

}
