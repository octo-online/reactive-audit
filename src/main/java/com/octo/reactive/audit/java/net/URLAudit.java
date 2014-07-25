package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.AbstractNetworkAudit;
import com.octo.reactive.audit.URLTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 2
@Aspect
public class URLAudit extends AbstractNetworkAudit
{
	@Before("call(java.io.InputStream java.net.URL.openStream())")
	public void openStream(JoinPoint thisJoinPoint)
	{
		URL url = (URL) thisJoinPoint.getTarget();
		AuditReactiveException ex = URLTools.latencyURL(config, thisJoinPoint, url);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(java.io.InputStream java.net.URL.openConnection())")
	public void openConnection(JoinPoint thisJoinPoint) // FIXME: TU
	{
		URL url = (URL) thisJoinPoint.getTarget();
		AuditReactiveException ex = URLTools.latencyURL(config, thisJoinPoint, url);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}
}
