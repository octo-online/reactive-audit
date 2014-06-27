package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class ServerSocketAudit extends NetworkAudit
{

	@Before("call(java.net.Socket java.net.ServerSocket.accept())")
	public void accept(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
