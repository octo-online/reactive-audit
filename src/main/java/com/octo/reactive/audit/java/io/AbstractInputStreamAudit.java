package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.Latency;
import org.aspectj.lang.JoinPoint;

import java.io.InputStream;

public class AbstractInputStreamAudit extends AbstractAudit
{
	protected void latency(Latency level, JoinPoint thisJoinPoint, InputStream in)
	{
		if (FileTools.isLastInputStreamWithLatency(in))
		{
			super.latency(level, thisJoinPoint);
		}
	}
}
