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
import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.FileTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

import java.io.Writer;

import static com.octo.reactive.audit.FileTools.*;

public abstract class AbstractWriterAudit extends AbstractFileAudit
{
	public static AuditReactiveException latencyWriter(AuditReactive config, Latency latency, JoinPoint thisJoinPoint, Writer writer)
			throws AuditReactiveException
	{
		AuditReactiveException ex = null;
		CharSequence msg = null;
		if (config.isDebug())
			msg = FileTools.dumpChain(writer);
		else
			msg = FileTools.printFilename(writer);
		switch (isLastOutputStreamFromWriterWithLatency(writer))
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

	protected void latency(Latency latency, JoinPoint thisJoinPoint, Writer writer) throws AuditReactiveException
	{
		AuditReactiveException ex = latencyWriter(config, latency, thisJoinPoint, writer);
		if (ex != null) super.logLatency(latency, thisJoinPoint, ex);
	}
}
