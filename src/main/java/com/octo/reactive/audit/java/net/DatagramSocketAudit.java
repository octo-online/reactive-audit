package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class DatagramSocketAudit extends NetworkAudit
{
	@Before("call(* java.net.DatagramSocket.connect(..))")
	public void connect(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* java.net.DatagramSocket.receive(..))")
	public void receive(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* java.net.DatagramSocket.send(..))")
	public void send(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
