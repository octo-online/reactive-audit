package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class DatagramSocketAudit extends NetworkAudit
{

	@Pointcut("call(* java.net.DatagramSocket.connect(..))")
	public void connect()
	{
	}

	@Pointcut("call(* java.net.DatagramSocket.receive(..))")
	public void receive()
	{
	}

	@Pointcut("call(* java.net.DatagramSocket.send(..))")
	public void send()
	{
	}

	@Before("(connect() || receive() || send())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
