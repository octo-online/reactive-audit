package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.Writer;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class WriterAspect extends AbstractWriterAudit
{
	@Before("call(* java.io.Writer.write(..))")
	public void write(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			Writer writer = (Writer) thisJoinPoint.getTarget();
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(HIGH, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}

}
