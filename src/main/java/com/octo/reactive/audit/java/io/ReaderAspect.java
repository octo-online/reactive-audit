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
    @LatencyLevel(LatencyLevel.LOW)
    public void advice_low(JoinPoint thisJoinPoint)
    {
	    if (isLastInputStreamInReaderWithLatency(thisJoinPoint))
		    lowLatency(thisJoinPoint);
    }

    @Before("(close())")
    @LatencyLevel(LatencyLevel.MEDIUM)
    public void advice_medium(JoinPoint thisJoinPoint)
    {
	    if (isLastInputStreamInReaderWithLatency(thisJoinPoint))
	        mediumLatency(thisJoinPoint);
    }

    @Before("(read())")
    @LatencyLevel(LatencyLevel.HIGH)
    public void advice_high(JoinPoint thisJoinPoint)
    {
	    if (isLastInputStreamInReaderWithLatency(thisJoinPoint))
	        highLatency(thisJoinPoint);
    }
}