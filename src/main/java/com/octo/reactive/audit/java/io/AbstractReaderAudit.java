package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

import java.io.Reader;

import static com.octo.reactive.audit.java.io.FileTools.FILE_ERROR;
import static com.octo.reactive.audit.java.io.FileTools.NET_ERROR;

class AbstractReaderAudit extends AbstractInputStreamAudit
{

	protected void latency(Latency level, JoinPoint thisJoinPoint, Reader reader)
	{
		AuditReactiveException ex = null;
		switch (FileTools.isLastInputStreamInReaderWithLatency(reader))
		{
			case NET_ERROR:
				ex = FactoryException.newNetwork(thisJoinPoint);
				break;
			case FILE_ERROR:
				ex = FactoryException.newFile(thisJoinPoint);
				break;

		}
		if (ex != null) super.latency(level, thisJoinPoint, ex);
	}
}
