package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractFileAudit;
import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.FileTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

import java.io.OutputStream;

import static com.octo.reactive.audit.FileTools.*;

public abstract class AbstractOutputStreamAudit extends AbstractFileAudit
{
	public static AuditReactiveException latencyOutputStream(AuditReactive config, Latency latency, JoinPoint thisJoinPoint, OutputStream out)
	{
		CharSequence msg = null;
		if (config.isDebug())
			msg = FileTools.dumpChain(out);
		else
			msg = FileTools.printFilename(out);
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

	/*package*/ void latency(Latency latency, JoinPoint thisJoinPoint, OutputStream out)
	{
		AuditReactiveException ex = latencyOutputStream(config, latency, thisJoinPoint, out);
		if (ex != null) super.logLatency(latency, thisJoinPoint, ex);
	}
}
