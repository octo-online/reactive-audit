package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.Latency;
import org.aspectj.lang.JoinPoint;

import java.io.Reader;

public class AbstractReaderAudit extends AbstractInputStreamAudit
{

	protected void latency(Latency level, JoinPoint thisJoinPoint, Reader reader)
	{
		if (FileTools.isLastInputStreamInReaderWithLatency(reader))
		{
			super.latency(level, thisJoinPoint);
		}
	}
}
