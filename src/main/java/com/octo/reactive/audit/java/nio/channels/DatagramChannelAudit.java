package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 18/06/2014.
 */
@Aspect
public class DatagramChannelAudit extends AbstractChannelsAudit
{
	@Before("call(java.nio.channels.DatagramChannel connect(java.net.SocketAddress))")
	public void connect(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
