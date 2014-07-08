package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.FileAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

import java.io.Writer;

import static com.octo.reactive.audit.java.io.FileTools.*;

class AbstractWriterAudit extends FileAudit
{
	protected void latency(Latency latency, JoinPoint thisJoinPoint, Writer writer) throws AuditReactiveException
	{
		AuditReactiveException ex = null;
		switch (isLastOutputStreamFromWriterWithLatency(writer))
		{
			case NET_ERROR:
				ex = FactoryException.newNetwork(latency, thisJoinPoint);
				break;
			case FILE_ERROR:
				ex = FactoryException.newFile(latency, thisJoinPoint);
				break;
		}
		if (ex != null) super.latency(latency, thisJoinPoint, ex);
	}
}
