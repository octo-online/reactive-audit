package com.octo.reactive.audit.javax.xml.parsers;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.URLTools;
import com.octo.reactive.audit.java.io.AbstractInputStreamAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.xml.sax.HandlerBase;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 7
@Aspect
public class SAXParserAudit extends AbstractFileAudit
{
	@Before("call(* javax.xml.parsers.SAXParser.parse(java.io.File,..))")
	public void parse(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* javax.xml.parsers.SAXParser.parse(java.io.InputStream,org.xml.sax.helpers.DefaultHandler)) && args(in,dh)")
	public void parse(JoinPoint thisJoinPoint, InputStream in, DefaultHandler dh)
	{
		AuditReactiveException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.parsers.SAXParser.parse(java.io.InputStream,org.xml.sax.HandlerBase)) && args(in,hb)")
	public void parse(JoinPoint thisJoinPoint, InputStream in, HandlerBase hb)
	{
		AuditReactiveException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.parsers.SAXParser.parse(java.io.InputStream,org.xml.sax.HandlerBase,String)) && args(in,hb,s)")
	public void parse(JoinPoint thisJoinPoint, InputStream in, HandlerBase hb, String s)
	{
		AuditReactiveException ex = AbstractInputStreamAudit.latencyInputStream(config, HIGH, thisJoinPoint, in);
		if (ex != null) super.logLatency(HIGH, thisJoinPoint, ex);
	}

	@Before("call(* javax.xml.parsers.SAXParser.parse(String,org.xml.sax.helpers.DefaultHandler)) && args(uri,dh)")
	public void parse(JoinPoint thisJoinPoint, String uri, DefaultHandler dh)
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

	@Before("call(* javax.xml.parsers.SAXParser.parse(String,org.xml.sax.HandlerBase)) && args(uri,hb)")
	public void parse(JoinPoint thisJoinPoint, String uri, HandlerBase hb)
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
