package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.aspectj.lang.JoinPoint;

import java.io.Writer;

import static com.octo.reactive.audit.java.io.FileTools.*;

public class AbstractWriterAudit extends AbstractOutputStreamAudit
{
	protected void latency(Latency level, JoinPoint thisJoinPoint, Writer writer) throws AuditReactiveException
	{
		switch (isLastOutputStreamFromWriterWithLatency(writer))
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
