package com.octo.reactive.audit.javax.net.ssl;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 5
@Aspect
public class SSLSocketAudit extends AbstractNetworkAudit
{
	@Before("call(javax.net.ssl.SSLSocket+.new(..))")
	public void New(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
