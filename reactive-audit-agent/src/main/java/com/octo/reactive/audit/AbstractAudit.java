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

import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;

public abstract class AbstractAudit
{
	protected final ReactiveAudit config = ReactiveAudit.config;

	private static boolean isReactiveThread()
	{
		return ReactiveAudit.config.isThreadNameMatch(Thread.currentThread().getName());
	}

	public static interface ExceptionFactory
	{
		public ReactiveAuditException lazyException();
	}
	abstract protected ReactiveAuditException newException(Latency latency, JoinPoint thisJoinPoint);

	protected void latency(final Latency latency,
						   final JoinPoint thisJoinPoint
						  )
			throws ReactiveAuditException
	{
		logLatency(latency, thisJoinPoint,new  ExceptionFactory()
		{
			public ReactiveAuditException lazyException()
			{
				return newException(latency, thisJoinPoint);
			}
		});
	}

	protected void logLatency(Latency latency,
							  JoinPoint thisJoinPoint,
							  ExceptionFactory ef)
			throws ReactiveAuditException
	{
		if (config.isStarted() && !config.isSuppressAudit() && isReactiveThread())
		{
			final ReactiveAudit config = ReactiveAudit.config;
			final ReactiveAuditException e=ef.lazyException();
			config.logIfNew(latency, e);
			if (config.isThrow())  // LOW, MEDIUM, HIGH ?
				throw e;
		}
	}


}
