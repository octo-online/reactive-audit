package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.OutputStream;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class OutputStreamAspect extends AbstractOutputStreamAudit
{
	@Pointcut("call(* java.io.OutputStream.close())")
	void close()
	{
	}

	@Pointcut("call(* java.io.OutputStream.flush())")
	void flush()
	{
	}

	@Pointcut("call(* java.io.OutputStream.write(..))")
	void write()
	{
	}

	@Before("(close() || flush() || write())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint, (OutputStream) thisJoinPoint.getTarget());
	}
}
