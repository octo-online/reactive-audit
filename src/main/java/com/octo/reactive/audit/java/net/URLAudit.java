package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.NetworkAudit;
import com.octo.reactive.audit.URLTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;

@Aspect
public class URLAudit extends NetworkAudit
{

	@Before("call(java.io.InputStream java.net.URL.openStream())")
	public void openStream(JoinPoint thisJoinPoint)
	{
		URL url = (URL) thisJoinPoint.getTarget();
		AuditReactiveException ex= URLTools.latencyURL(config, thisJoinPoint, url);
		if (ex!=null) super.latency(HIGH,thisJoinPoint,ex);
	}
}
