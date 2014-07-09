package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.LOW;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;

@Aspect
public class URLAudit extends NetworkAudit
{

	@Before("call(java.io.InputStream java.net.URL.openStream())")
	public void openStream(JoinPoint thisJoinPoint)
	{
		URL url = (URL) thisJoinPoint.getTarget();
		String s = url.toExternalForm();
		CharSequence msg = null;
		if (config.isDebug())
		{
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + thisJoinPoint.getTarget());
			msg = thisJoinPoint.getTarget().toString();
		}
		if (s.startsWith("jar:file:") || s.startsWith("file:"))
			latency(LOW, thisJoinPoint, FactoryException.newFile(LOW, thisJoinPoint, msg));
		else
			latency(MEDIUM, thisJoinPoint, FactoryException.newNetwork(MEDIUM, thisJoinPoint, msg));
	}
}
