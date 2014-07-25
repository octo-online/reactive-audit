package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.FileTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.Writer;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods : 23
@Aspect
public class PrintWriterAudit extends AbstractWriterAudit
{
	@Before("call(* java.io.PrintWriter.format(..))")
	public void format(JoinPoint thisJoinPoint)
	{
		Writer writer = (Writer) thisJoinPoint.getTarget();
		if (config.isDebug())
		{
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(HIGH, thisJoinPoint, writer);
	}

	@Before("call(* java.io.PrintWriter.print(..))")
	public void print(JoinPoint thisJoinPoint)
	{
		Writer writer = (Writer) thisJoinPoint.getTarget();
		if (config.isDebug())
		{
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(HIGH, thisJoinPoint, writer);
	}

	@Before("call(* java.io.PrintWriter.printf(..))")
	public void printf(JoinPoint thisJoinPoint)
	{
		Writer writer = (Writer) thisJoinPoint.getTarget();
		if (config.isDebug())
		{
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(HIGH, thisJoinPoint, writer);
	}

	@Before("call(* java.io.PrintWriter.println(..))")
	public void println(JoinPoint thisJoinPoint)
	{
		Writer writer = (Writer) thisJoinPoint.getTarget();
		if (config.isDebug())
		{
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(writer));
		}
		latency(HIGH, thisJoinPoint, writer);
	}

}
