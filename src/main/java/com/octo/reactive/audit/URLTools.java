package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;

import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.LOW;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;

public class URLTools
{
	public static AuditReactiveException latencyURL(AuditReactive config, JoinPoint thisJoinPoint, URL url)
	{
		AuditReactiveException e;
		String s = url.toExternalForm();
		CharSequence msg = null;
		if (config.isDebug())
		{
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + thisJoinPoint.getTarget());
			msg = thisJoinPoint.getTarget().toString();
		}
		if (s.startsWith("jar:file:") || s.startsWith("file:"))
			e = FactoryException.newFile(LOW, thisJoinPoint, msg);
		else
			e = FactoryException.newNetwork(MEDIUM, thisJoinPoint, msg);
		return e;

	}
}
