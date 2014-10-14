/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.AbstractNetworkAudit;
import com.octo.reactive.audit.NetworkTools;
import com.octo.reactive.audit.URLTools;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.net.URL;
import java.net.URLConnection;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 20
@Aspect
public class URLConnectionAudit extends AbstractNetworkAudit
{

	@Before("call(void java.net.URLConnection.connect())")
	public void connect(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(Object java.net.URLConnection.getContent())")
	public void getContent(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(Object java.net.URLConnection.getContent(Class[]))")
	public void getContent_Class(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(String java.net.URLConnection.getContentEncoding())")
	public void getContentEncoding(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(int java.net.URLConnection.getContentLength())")
	public void getContentLength(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(long java.net.URLConnection.getContentLengthLong())")
	public void getContentLengthLong(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(String java.net.URLConnection.getContentType())")
	public void getContentType(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(long java.net.URLConnection+.getDate())")
	public void getDate(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(long java.net.URLConnection.getExpiration())")
	public void getExpiration(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(String java.net.URLConnection.getHeaderField(int))")
	public void getHeaderField_i(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(String java.net.URLConnection.getHeaderField(String))")
	public void getHeaderField_String(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(long java.net.URLConnection.getHeaderFieldDate(String, long))")
	public void getHeaderFieldDate(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(int java.net.URLConnection.getHeaderFieldInt(String, int))")
	public void getHeaderFieldInt(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(String java.net.URLConnection.getHeaderFieldKey(int))")
	public void getHeaderFieldKey(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(long java.net.URLConnection.getHeaderFieldLong(String, long))")
	public void getHeaderFieldLong(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* java.net.URLConnection.getHeaderFields())")
	public void getHeaderFields(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(long java.net.URLConnection.getIfModifiedSince())")
	public void getIfModifiedSince(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(java.io.InputStream java.net.URLConnection.getInputStream())")
	public void getInputStream(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(long java.net.URLConnection.getLastModified())")
	public void getLastModified(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(java.io.OutputStream java.net.URLConnection.getOutputStream())")
	public void getOutputStream(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Override
	protected void latency(Latency latency, JoinPoint thisJoinPoint)
			throws ReactiveAuditException
	{
		URL url = ((URLConnection) thisJoinPoint.getTarget()).getURL();
		if (url!=null)
			super.logLatency(HIGH, thisJoinPoint, URLTools.latencyURL(config, thisJoinPoint, url));
		else
			if (!NetworkTools.isURLConnected((URLConnection) thisJoinPoint.getTarget()))
				super.latency(HIGH, thisJoinPoint);
	}
}
