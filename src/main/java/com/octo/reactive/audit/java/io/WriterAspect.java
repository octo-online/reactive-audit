package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.*;

@Aspect
public class WriterAspect extends AbstractWriterAudit
{
	@Pointcut("call(* java.io.Writer.append(..))")
    public void append(){ }
	@Pointcut("call(* java.io.Writer.close())")
    public void close() { }
	@Pointcut("call(* java.io.Writer.flush())")
	public void flush() { }
	@Pointcut("call(* java.io.Writer.write(..))")
	public void write() { }

    @Before("(append() || close())")
    @LatencyLevel(LatencyLevel.MEDIUM)
    public void advice_medium(JoinPoint thisJoinPoint)
    {
	    if (isLastOutputStreamFromWriterWithLatency(thisJoinPoint))
	        mediumLatency(thisJoinPoint);
    }

    @Before("(write() || flush())")
    @LatencyLevel(LatencyLevel.HIGH)
    public void advice_high(JoinPoint thisJoinPoint)
    {
	    if (isLastOutputStreamFromWriterWithLatency(thisJoinPoint))
	        highLatency(thisJoinPoint);
    }
}