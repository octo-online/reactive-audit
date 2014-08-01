package com.octo.reactive.audit.javax.xml.parsers;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.URLTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 2
@Aspect
public class DocumentBuilderAudit extends AbstractFileAudit
{
	@Before("call(* javax.xml.parsers.DocumentBuilder.parse(java.io.File))")
	public void parse(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* javax.xml.parsers.DocumentBuilder.parse(String)) && args(uri)")
	public void parse(JoinPoint thisJoinPoint, String uri)
	{
		try
		{
			URL url = new URI(uri).toURL();
			AuditReactiveException ex = URLTools.latencyURL(config, thisJoinPoint, url);
			if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
		}
		catch (URISyntaxException e)
		{
			// Ignore
		}
		catch (MalformedURLException e)
		{
			// Ignore
		}
	}
}
