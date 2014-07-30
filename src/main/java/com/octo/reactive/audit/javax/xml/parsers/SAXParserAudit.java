package com.octo.reactive.audit.javax.xml.parsers;

import com.octo.reactive.audit.FileAudit;
import com.octo.reactive.audit.URLTools;
import com.octo.reactive.audit.java.io.AbstractInputStreamAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class SAXParserAudit extends FileAudit
{
	@Before("call(* javax.xml.parsers.SAXParser.parse(java.io.File,..))")
	public void parse(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* javax.xml.parsers.SAXParser.parse(java.io.InputStream,..)) && args(in)")
	public void parse(JoinPoint thisJoinPoint, InputStream in)
	{
		AuditReactiveException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.parsers.SAXParser.parse(String,..)) && args(uri)")
	public void parse(JoinPoint thisJoinPoint, String uri)
	{
		try
		{
			URL url = new URI(uri).toURL();
			AuditReactiveException ex = URLTools.latencyURL(config, thisJoinPoint, url);
			if (ex != null) super.latency(HIGH, thisJoinPoint, ex);
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
