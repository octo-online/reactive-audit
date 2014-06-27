package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.FactoryException;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.*;
import java.nio.channels.InterruptibleChannel;

import static com.octo.reactive.audit.java.io.FileTools.*;
import static com.octo.reactive.audit.lib.Latency.LOW;

@Aspect
public class AppendableAspect extends AbstractAudit
{
	@Before("call(* java.io.Appendable.append(..)) && target(java.io.Writer)")
	public void append(JoinPoint thisJoinPoint)
	{
		final Object target = thisJoinPoint.getTarget();
		Latency level = LOW;
		AuditReactiveException ex = null;
		if (target instanceof InterruptibleChannel)
			return;
		if (target instanceof InputStream)
		{
			switch (isLastInputStreamWithLatency((InputStream) target))
			{
				case NET_ERROR:
					ex = FactoryException.newNetwork(thisJoinPoint);
					break;
				case FILE_ERROR:
					ex = FactoryException.newFile(thisJoinPoint);
					break;
			}
		}
		else if (target instanceof OutputStream)
		{
			switch (isLastOutputStreamWithLatency((OutputStream) target))
			{
				case NET_ERROR:
					ex = FactoryException.newNetwork(thisJoinPoint);
					break;
				case FILE_ERROR:
					ex = FactoryException.newFile(thisJoinPoint);
					break;
			}
		}
		else if (target instanceof Reader)
		{
			switch (isLastInputStreamInReaderWithLatency((Reader) target))
			{
				case NET_ERROR:
					ex = FactoryException.newNetwork(thisJoinPoint);
					break;
				case FILE_ERROR:
					ex = FactoryException.newFile(thisJoinPoint);
					break;
			}
		}
		else if (target instanceof Writer)
		{
			switch (isLastOutputStreamFromWriterWithLatency((Writer) target))
			{
				case NET_ERROR:
					ex = FactoryException.newNetwork(thisJoinPoint);
					break;
				case FILE_ERROR:
					ex = FactoryException.newFile(thisJoinPoint);
					break;
			}
		}
		else if (target instanceof RandomAccessFile)
		{
			ex = FactoryException.newFile(thisJoinPoint);
		}
		if (ex != null) latency(LOW, thisJoinPoint, ex);
	}

	@Override
	protected AuditReactiveException newException(JoinPoint thisJoinPoint)
	{
		return null; // FIXME
	}
}
