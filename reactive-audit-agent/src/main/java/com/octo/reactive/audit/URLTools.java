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

package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;

import java.net.URL;

import static com.octo.reactive.audit.lib.Latency.LOW;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;

public final class URLTools
{
	private URLTools()
	{
	}

	public static AbstractAudit.ExceptionFactory latencyURL(ReactiveAudit config, final JoinPoint thisJoinPoint, URL url)
	{
		ReactiveAuditException e;
		String s = url.toExternalForm();
		CharSequence msg = null;
		if (config.isDebug())
		{
			config.logger.finest(thisJoinPoint.toString() + "  with " + url);
			msg = url.toString();
		}
		final boolean fileEx=(s.startsWith("jar:file:") || s.startsWith("file:"));
		final CharSequence fmsg=msg;
		return new AbstractAudit.ExceptionFactory()
		{
			public ReactiveAuditException lazyException()
			{
				return fileEx
					   ? FactoryException.newFile(LOW, thisJoinPoint, fmsg)
					   : FactoryException.newNetwork(MEDIUM, thisJoinPoint, fmsg);
			}
		};
	}
}
