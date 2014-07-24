package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.FileAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

import java.io.InputStream;

import static com.octo.reactive.audit.java.io.FileTools.FILE_ERROR;
import static com.octo.reactive.audit.java.io.FileTools.NET_ERROR;

public class AbstractInputStreamAudit extends FileAudit
{
	protected void latency(Latency latency, JoinPoint thisJoinPoint, InputStream in)
	{
		AuditReactiveException ex=latencyInputStream(config,latency,thisJoinPoint,in);
		if (ex!=null) super.latency(latency,thisJoinPoint,ex);
	}
	static public AuditReactiveException latencyInputStream(AuditReactive config,Latency latency, JoinPoint thisJoinPoint, InputStream in)
	{
		CharSequence msg = null;
		if (config.isDebug())
			msg = FileTools.dumpChain(in);
		AuditReactiveException ex = null;
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
}
