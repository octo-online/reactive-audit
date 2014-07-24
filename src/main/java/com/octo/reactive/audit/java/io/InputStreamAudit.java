package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.FileTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

@Aspect
public class InputStreamAudit extends AbstractInputStreamAudit
{
	@Before("call(* java.io.InputStream+.available())")
	public void available(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint, (InputStream) thisJoinPoint.getTarget());
	}

	@Before("call(* java.io.InputStream+.skip(long))")
	public void skip(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint, (InputStream) thisJoinPoint.getTarget());
	}

	@Before("call(* java.io.InputStream+.read(..))")
	public void read(JoinPoint thisJoinPoint)
	{
		if (config.isDebug())
		{
			InputStream in = (InputStream) thisJoinPoint.getTarget();
			config.logger.finest(
					thisJoinPoint.toString() + "  with " + FileTools.dumpChain(in));
		}
		latency(HIGH, thisJoinPoint, (InputStream) thisJoinPoint.getTarget());
	}

}
