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

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.FileTools;
import com.octo.reactive.audit.ReactiveAudit;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.aspectj.lang.JoinPoint;

import java.io.InputStream;

import static com.octo.reactive.audit.FileTools.FILE_ERROR;
import static com.octo.reactive.audit.FileTools.NET_ERROR;

public abstract class AbstractInputStreamAudit extends AbstractFileAudit
{
	static public ReactiveAuditException latencyInputStream(ReactiveAudit config, Latency latency,
															JoinPoint thisJoinPoint, InputStream in)
	{
		CharSequence msg;
		if (config.isDebug())
			msg = FileTools.dumpChain(in);
		else
			msg = FileTools.printFilename(in);
		ReactiveAuditException ex = null;
		switch (FileTools.isLastInputStreamWithLatency(in))
		{
			case NET_ERROR:
				ex = FactoryException.newNetwork(latency, thisJoinPoint, msg);
				break;
			case FILE_ERROR:
				ex = FactoryException.newFile(latency, thisJoinPoint, msg);
				break;
		}
		return ex;
	}

	/*package*/ void latency(Latency latency, JoinPoint thisJoinPoint, InputStream in)
	{
		final ReactiveAuditException ex = latencyInputStream(config, latency, thisJoinPoint, in);
		if (ex != null) super.logLatency(latency, thisJoinPoint, new  ExceptionFactory()
		{
			public ReactiveAuditException lazyException()
			{
				return ex;
			}
		});
	}
}
