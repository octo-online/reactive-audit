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

package com.octo.reactive.audit.javax.xml.parsers;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.URLTools;
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
			super.logLatency(HIGH, thisJoinPoint, URLTools.latencyURL(config, thisJoinPoint, url));
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
