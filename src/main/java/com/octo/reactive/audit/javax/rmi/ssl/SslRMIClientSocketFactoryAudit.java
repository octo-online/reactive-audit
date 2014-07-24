package com.octo.reactive.audit.javax.rmi.ssl;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class SslRMIClientSocketFactoryAudit extends NetworkAudit
{
	@Before("call(* javax.rmi.ssl.SslRMIClientSocketFactory.createSocket(..))")
	public void createSocket(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
