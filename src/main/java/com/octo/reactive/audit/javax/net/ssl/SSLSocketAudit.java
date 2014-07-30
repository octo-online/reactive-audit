package com.octo.reactive.audit.javax.net.ssl;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class SSLSocketAudit extends NetworkAudit
{
	@Before("call(javax.net.ssl.SSLSocket.new(..))")
	public void New(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
