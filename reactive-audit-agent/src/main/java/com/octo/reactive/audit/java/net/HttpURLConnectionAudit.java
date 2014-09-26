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
import com.octo.reactive.audit.lib.ReactiveAuditException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.net.URLConnection;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 3
@Aspect
public class HttpURLConnectionAudit extends AbstractNetworkAudit
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
	protected void latency(Latency latency, JoinPoint thisJoinPoint)
			throws ReactiveAuditException
	{
		if (!NetworkTools.isURLConnected((URLConnection) thisJoinPoint.getTarget()))
			super.latency(HIGH, thisJoinPoint);
	}
}
