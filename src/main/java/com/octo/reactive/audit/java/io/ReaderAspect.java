package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.*;

@Aspect
public class ReaderAspect extends AbstractReaderAudit
{
	@Pointcut("call(* java.io.Reader.read(..))")
    public void read(){ }
	@Pointcut("call(* java.io.Reader.skip(..))")
	public void skip(){ }
	@Pointcut("call(* java.io.Reader.close())")
    public void close() { }

	@Before("(skip())")
    public void advice_low(JoinPoint thisJoinPoint)
    {
		latency(LatencyLevel.LOW,thisJoinPoint,(Reader)thisJoinPoint.getTarget());
    }

    @Before("(close())")
    public void advice_medium(JoinPoint thisJoinPoint)
    {
	    latency(LatencyLevel.MEDIUM,thisJoinPoint,(Reader)thisJoinPoint.getTarget());
    }

    @Before("(read())")
    public void advice_high(JoinPoint thisJoinPoint)
    {
        latency(LatencyLevel.HIGH,thisJoinPoint,(Reader)thisJoinPoint.getTarget());
    }
}