package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.net.URLConnection;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class URLConnectionAudit extends NetworkAudit
{

	@Pointcut("call(void java.net.URLConnection.connect())")
	public void connect()
	{
	}

	@Pointcut("call(Object java.net.URLConnection.getContent())")
	public void getContent()
	{
	}

	@Pointcut("call(Object java.net.URLConnection.getContent(Class[]))")
	public void getContent_Class()
	{
	}

	@Pointcut("call(String java.net.URLConnection.getContentEncoding())")
	public void getContentEncoding()
	{
	}

	@Pointcut("call(int java.net.URLConnection.getContentLength())")
	public void getContentLength()
	{
	}

	@Pointcut("call(long java.net.URLConnection.getContentLengthLong())")
	public void getContentLengthLong()
	{
	}

	@Pointcut("call(String java.net.URLConnection.getContentType())")
	public void getContentType()
	{
	}

	@Pointcut("call(long java.net.URLConnection+.getDate())")
	public void getDate()
	{
	}

	@Pointcut("call(long java.net.URLConnection.getExpiration())")
	public void getExpiration()
	{
	}

	@Pointcut("call(String java.net.URLConnection.getHeaderField(int))")
	public void getHeaderField_i()
	{
	}

	@Pointcut("call(String java.net.URLConnection.getHeaderField(String))")
	public void getHeaderField_String()
	{
	}

	@Pointcut("call(long java.net.URLConnection.getHeaderFieldDate(String, long))")
	public void getHeaderFieldDate()
	{
	}

	@Pointcut("call(int java.net.URLConnection.getHeaderFieldInt(String, int))")
	public void getHeaderFieldInt()
	{
	}

	@Pointcut("call(String java.net.URLConnection.getHeaderFieldKey(int))")
	public void getHeaderFieldKey()
	{
	}

	@Pointcut("call(long java.net.URLConnection.getHeaderFieldLong(String, long))")
	public void getHeaderFieldLong()
	{
	}

	@Pointcut("call(* java.net.URLConnection.getHeaderFields())")
	public void getHeaderFields()
	{
	}

	@Pointcut("call(long java.net.URLConnection.getIfModifiedSince())")
	public void getIfModifiedSince()
	{
	}

	@Pointcut("call(java.io.InputStream java.net.URLConnection.getInputStream())")
	public void getInputStream()
	{
	}

	@Pointcut("call(long java.net.URLConnection.getLastModified())")
	public void getLastModified()
	{
	}

	@Pointcut("call(java.io.OutputStream java.net.URLConnection.getOutputStream())")
	public void getOutputStream()
	{
	}

	@Before("(connect() || getContent() || getContent_Class() ||" +
			        "getContentEncoding() || getContentLength() || getContentLengthLong() || " +
			        "getContentType() || getDate() || getExpiration() ||" +
			        "getHeaderField_i() || getHeaderField_String() || " +
			        "getHeaderFieldDate() || getHeaderFieldInt() || getHeaderFieldKey() ||" +
			        "getHeaderFieldLong() || getHeaderFields() || getIfModifiedSince() ||" +
			        "getInputStream() || getLastModified() || getOutputStream())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		final URLConnection conn = (URLConnection) thisJoinPoint.getTarget();
		if (!NetworkTools.isURLConnected(conn))
			latency(HIGH, thisJoinPoint);
	}

}
