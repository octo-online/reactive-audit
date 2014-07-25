package com.octo.reactive.audit.java.nio.channels;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 2
@Aspect
public class SelectorAudit extends AbstractChannelsAudit
{
	@Before("call(int java.nio.channels.Selector.select(..))")
	public void select(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			latency(HIGH, thisJoinPoint);
		}
	}

}
