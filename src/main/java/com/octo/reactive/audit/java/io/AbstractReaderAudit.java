package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.FileTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

import java.io.Reader;

import static com.octo.reactive.audit.FileTools.FILE_ERROR;
import static com.octo.reactive.audit.FileTools.NET_ERROR;

public abstract class AbstractReaderAudit extends AbstractInputStreamAudit
{

	public static AuditReactiveException latencyReader(AuditReactive config, Latency latency, JoinPoint thisJoinPoint, Reader reader)
	{
		CharSequence msg = null;
		if (config.isDebug())
			msg = FileTools.dumpChain(reader);
		else
			msg = FileTools.printFilename(reader);
		AuditReactiveException ex = null;
		switch (FileTools.isLastReaderWithLatency(reader))
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

	protected void latency(Latency latency, JoinPoint thisJoinPoint, Reader reader)
	{
		AuditReactiveException ex = latencyReader(config, latency, thisJoinPoint, reader);
		if (ex != null) super.logLatency(latency, thisJoinPoint, ex);
	}
}
