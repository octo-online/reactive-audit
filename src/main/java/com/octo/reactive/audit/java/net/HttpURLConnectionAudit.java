package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.NetworkAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.net.URLConnection;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class HttpURLConnectionAudit extends NetworkAudit
{

	@Before("call(int java.net.HttpURLConnection.getResponseCode())")
	public void getResponseCode(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(String java.net.HttpURLConnection.getResponseMessage())")
	public void getResponseMessage(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(java.io.InputStream java.net.HttpURLConnection.getErrorStream())")
	public void getErrorStream(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Override
	protected void latency(Latency latency, JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		if (!NetworkTools.isURLConnected((URLConnection) thisJoinPoint.getTarget()))
			super.latency(HIGH, thisJoinPoint);
	}
}
