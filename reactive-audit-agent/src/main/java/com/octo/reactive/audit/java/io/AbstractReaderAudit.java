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

package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.FileTools;
import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;

import java.io.Reader;

import static com.octo.reactive.audit.FileTools.FILE_ERROR;
import static com.octo.reactive.audit.FileTools.NET_ERROR;

public abstract class AbstractReaderAudit extends AbstractInputStreamAudit
{

	public static ExceptionFactory latencyReader(final ReactiveAudit config,
												 final Latency latency,
												 final JoinPoint thisJoinPoint,
												 final Reader reader)
	{
		ExceptionFactory ef=null;
		ReactiveAuditException ex = null;
		switch (FileTools.isLastReaderWithLatency(reader))
		{
			case NET_ERROR:
				ef=new ExceptionFactory()
				{
					@Override
					public ReactiveAuditException lazyException()
					{
						CharSequence msg=(config.isDebug())
										 ? FileTools.dumpChain(reader)
										 : FileTools.printFilename(reader);
						return FactoryException.newNetwork(latency, thisJoinPoint, msg);
					}
				};
				break;
			case FILE_ERROR:
				ef=new ExceptionFactory()
				{
					@Override
					public ReactiveAuditException lazyException()
					{
						CharSequence msg=(config.isDebug())
										 ? FileTools.dumpChain(reader)
										 : FileTools.printFilename(reader);
						return FactoryException.newFile(latency, thisJoinPoint, msg);
					}
				};
				break;

		}
		return ef;
	}

	protected void latency(Latency latency, JoinPoint thisJoinPoint, Reader reader)
	{
		final ExceptionFactory ef = latencyReader(config, latency, thisJoinPoint, reader);
		if (ef != null) super.logLatency(latency, thisJoinPoint, ef);
	}
}
