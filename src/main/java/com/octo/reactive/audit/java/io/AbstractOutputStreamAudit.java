package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.FileAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

import java.io.OutputStream;

import static com.octo.reactive.audit.java.io.FileTools.*;

public class AbstractOutputStreamAudit extends FileAudit
{
	protected void latency(Latency latency, JoinPoint thisJoinPoint, OutputStream out)
	{
		AuditReactiveException ex = latencyOutputStream(config,latency,thisJoinPoint,out);
		if (ex != null) super.latency(latency, thisJoinPoint, ex);
	}
	public static AuditReactiveException latencyOutputStream(AuditReactive config,Latency latency, JoinPoint thisJoinPoint, OutputStream out)
	{
		CharSequence msg = null;
		if (config.isDebug())
			msg = FileTools.dumpChain(out);
		AuditReactiveException ex = null;
		switch (isLastOutputStreamWithLatency(out))
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
