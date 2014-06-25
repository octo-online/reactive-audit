package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.aspectj.lang.JoinPoint;

import java.io.Reader;

import static com.octo.reactive.audit.java.io.FileTools.FILE_ERROR;
import static com.octo.reactive.audit.java.io.FileTools.NET_ERROR;

class AbstractReaderAudit extends AbstractInputStreamAudit
{

	protected void latency(Latency level, JoinPoint thisJoinPoint, Reader reader)
	{
		switch (FileTools.isLastInputStreamInReaderWithLatency(reader))
		{
			case NET_ERROR:
				super.latency(level, thisJoinPoint,
				              new NetworkAuditReactiveException(thisJoinPoint.getSignature().toString()));
				break;
			case FILE_ERROR:
				super.latency(level, thisJoinPoint);
				break;
			default:
				// Nothing

		}
	}
}
