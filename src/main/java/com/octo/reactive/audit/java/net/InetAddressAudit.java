package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.LOW;

@Aspect
public class InetAddressAudit extends AbstractNetworkAudit
{

	@Before("call(static java.net.InetAddress[] java.net.InetAddress.getAllByName(String))")
	public void getAllByName(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(static java.net.InetAddress java.net.InetAddress.getByName(String))")
	public void getByName(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(String java.net.InetAddress.getHostName())")
	public void getHostName(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}
}
