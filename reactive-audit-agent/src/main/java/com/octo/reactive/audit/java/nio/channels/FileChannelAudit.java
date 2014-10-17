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

package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 4
@Aspect
public class FileChannelAudit extends AbstractChannelsAudit
{
	@Before("call(java.nio.channels.FileChannel java.nio.channels.FileChannel.open(..))")
	public void open(JoinPoint thisJoinPoint)
	{
		final FileReactiveAuditException ex=FactoryException.newFile(MEDIUM, thisJoinPoint);
		logLatency(MEDIUM, thisJoinPoint,new  ExceptionFactory()
		{
			public ReactiveAuditException lazyException()
			{
				return ex;
			}
		} );
	}

	@Before("call(java.nio.channels.FileLock java.nio.channels.FileChannel.lock(..))")
	public void lock(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
