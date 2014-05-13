package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.FileInputStream;
import java.io.InputStream;

@Aspect
public class InputStreamAspect extends AbstractInputStreamAudit
{
    @Pointcut("call(* java.io.InputStream.available())")
    public void available() { }
    @Pointcut("call(* java.io.InputStream.read(..))")
    public void read(){ }
    @Pointcut("call(* java.io.InputStream.skip(long))")
    public void skip() { }
	@Pointcut("call(* java.io.InputStream.close())")
    public void close() { }

	@Before("(available() || skip())")
    @LatencyLevel(LatencyLevel.LOW)
    public void advice_low(JoinPoint thisJoinPoint)
    {
	    if (isLastInputStreamWithLatency((InputStream) thisJoinPoint.getTarget()))
	        lowLatency(thisJoinPoint);
    }

    @Before("(close())")
    @LatencyLevel(LatencyLevel.MEDIUM)
    public void advice_medium(JoinPoint thisJoinPoint)
    {
	    if (isLastInputStreamWithLatency((InputStream) thisJoinPoint.getTarget()))
	        mediumLatency(thisJoinPoint);
    }

    @Before("(read())")
    @LatencyLevel(LatencyLevel.HIGH)
    public void advice_high(JoinPoint thisJoinPoint)
    {
	    if (isLastInputStreamWithLatency((InputStream) thisJoinPoint.getTarget()))
		    highLatency(thisJoinPoint);
    }
}