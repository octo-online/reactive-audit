package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.FileAudit;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;

import java.io.OutputStream;

import static com.octo.reactive.audit.java.io.FileTools.*;

class AbstractOutputStreamAudit extends FileAudit
{
	protected void latency(Latency level, JoinPoint thisJoinPoint, OutputStream out)
	{
		AuditReactiveException ex = null;
		switch (isLastOutputStreamWithLatency(out))
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
