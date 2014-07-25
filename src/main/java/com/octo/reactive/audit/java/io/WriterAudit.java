package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.FileTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.Writer;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

// Nb methods : 10
@Aspect
public class WriterAudit extends AbstractWriterAudit
{
	@Before("call(* java.io.Writer.append(..))")
	public void append(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			Writer writer = (Writer) thisJoinPoint.getTarget();
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(HIGH, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}

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

	@Before("call(* java.io.Writer.flush(..))")
	public void flush(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			Writer writer = (Writer) thisJoinPoint.getTarget();
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(HIGH, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}

	@Before("call(* java.io.Writer.close())")
	public void close(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			Writer writer = (Writer) thisJoinPoint.getTarget();
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(LOW, thisJoinPoint, (Writer) thisJoinPoint.getTarget());
	}

}
