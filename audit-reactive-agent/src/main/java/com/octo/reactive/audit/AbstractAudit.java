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

import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

public abstract class AbstractAudit
{
	protected final AuditReactive config = AuditReactive.config;

	private static boolean isReactiveThread()
	{
		return AuditReactive.config.isThreadNameMatch(Thread.currentThread().getName());
	}

	abstract protected AuditReactiveException newException(Latency latency, JoinPoint thisJoinPoint);

	private boolean checkForAll()
	{
		return isReactiveThread() && !config.isSuppressAudit();
	}

	protected void latency(Latency latency,
	                       JoinPoint thisJoinPoint
	) throws AuditReactiveException
	{
		logLatency(latency, thisJoinPoint, newException(latency, thisJoinPoint));
	}

	protected void logLatency(Latency latency,
	                          JoinPoint thisJoinPoint,
	                          AuditReactiveException e)
			throws AuditReactiveException
	{
		if (checkForAll())
		{
			final AuditReactive config = AuditReactive.config;
			if (!config.isAfterStartupDelay())
				return;
			config.logIfNew(latency, e);
			if (config.isThrow())  // LOW, MEDIUM, HIGH ?
				throw e;
		}
	}


}
