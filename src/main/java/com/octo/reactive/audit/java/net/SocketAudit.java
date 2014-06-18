package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.net.Socket;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

@Aspect
public class SocketAudit extends NetworkAudit
{

	@Before("call(java.net.Socket+.new(java.net.InetAddress,..))")
	public void new_1(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(java.net.Socket+.new(String,..))")
	public void new_2(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(void sendUrgentData(int))")
	public void sendUrgentData(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(void java.net.Socket.connect(..))")
	public void advice_low(JoinPoint thisJoinPoint)
	{
		Socket socket = (Socket) thisJoinPoint.getTarget();
		if (!socket.isConnected())
		{
			latency(MEDIUM, thisJoinPoint);
		}
	}

}
