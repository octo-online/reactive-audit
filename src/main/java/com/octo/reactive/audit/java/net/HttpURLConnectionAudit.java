package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.net.URLConnection;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class HttpURLConnectionAudit extends NetworkAudit
{

	@Pointcut("call(int java.net.HttpURLConnection.getResponseCode())")
	public void getResponseCode()
	{
	}

	@Pointcut("call(String java.net.HttpURLConnection.getResponseMessage())")
	public void getResponseMessage()
	{
	}

	@Pointcut("call(java.io.InputStream java.net.HttpURLConnection.getErrorStream())")
	public void getErrorStream()
	{
	}


	@Before("(getResponseCode() || getResponseMessage() || getErrorStream())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		final URLConnection conn = (URLConnection) thisJoinPoint.getTarget();
		if (!NetworkTools.isURLConnected(conn))
			latency(HIGH, thisJoinPoint);
	}

}
