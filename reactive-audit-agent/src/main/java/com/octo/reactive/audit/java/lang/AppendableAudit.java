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

package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.*;
import java.nio.channels.InterruptibleChannel;

import static com.octo.reactive.audit.FileTools.*;
import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods: 3
@Aspect
public class AppendableAudit extends AbstractAudit
{
	@SuppressWarnings("ChainOfInstanceofChecks")
	@Before("call(* java.lang.Appendable.append(..)) && target(java.io.Writer)")
	public void append(JoinPoint thisJoinPoint)
	{
		final Object target = thisJoinPoint.getTarget();
		Latency latency = LOW;
		ReactiveAuditException ex = null;
		if (target instanceof InterruptibleChannel)
			return;
		if (target instanceof InputStream)
		{
			switch (isLastInputStreamWithLatency((InputStream) target))
			{
				case NET_ERROR:
					ex = FactoryException.newNetwork(latency, thisJoinPoint);
					break;
				case FILE_ERROR:
					ex = FactoryException.newFile(latency, thisJoinPoint);
					break;
			}
		}
		else if (target instanceof OutputStream)
		{
			switch (isLastOutputStreamWithLatency((OutputStream) target))
			{
				case NET_ERROR:
					ex = FactoryException.newNetwork(latency, thisJoinPoint);
					break;
				case FILE_ERROR:
					ex = FactoryException.newFile(latency, thisJoinPoint);
					break;
			}
		}
		else if (target instanceof Reader)
		{
			switch (isLastReaderWithLatency((Reader) target))
			{
				case NET_ERROR:
					ex = FactoryException.newNetwork(latency, thisJoinPoint);
					break;
				case FILE_ERROR:
					ex = FactoryException.newFile(latency, thisJoinPoint);
					break;
			}
		}
		else if (target instanceof Writer)
		{
			switch (isLastOutputStreamFromWriterWithLatency((Writer) target))
			{
				case NET_ERROR:
					ex = FactoryException.newNetwork(latency, thisJoinPoint);
					break;
				case FILE_ERROR:
					ex = FactoryException.newFile(latency, thisJoinPoint);
					break;
			}
		}
		else if (target instanceof RandomAccessFile)
		{
			ex = FactoryException.newFile(latency, thisJoinPoint);
		}
		final ReactiveAuditException ee=ex;
		if (ex != null) logLatency(LOW, thisJoinPoint, new  ExceptionFactory()
		{
			public ReactiveAuditException lazyException()
			{
				return ee;
			}
		});
	}

	@Override
	protected ReactiveAuditException newException(Latency latency, JoinPoint thisJoinPoint)
	{
		return null;
	}
}
