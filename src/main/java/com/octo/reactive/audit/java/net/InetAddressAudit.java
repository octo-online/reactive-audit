package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import static com.octo.reactive.audit.lib.Latency.LOW;

@Aspect
public class InetAddressAudit extends NetworkAudit
{

	@Pointcut("call(static java.net.InetAddress[] java.net.InetAddress.getAllByName(String))")
	public void getAllByName()
	{
	}

	@Pointcut("call(static java.net.InetAddress java.net.InetAddress.getByName(String))")
	public void getByName()
	{
	}

	@Pointcut("call(String java.net.InetAddress.getHostName())")
	public void getHostName()
	{
	}


	@Before("(getAllByName() || getByName() || getHostName())")
	public void advice_low(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

}
