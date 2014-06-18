package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class ServerSocketAudit extends NetworkAudit
{

	@Pointcut("call(java.net.Socket java.net.ServerSocket.accept())")
	public void accept()
	{
	}


	@Before("(accept())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
