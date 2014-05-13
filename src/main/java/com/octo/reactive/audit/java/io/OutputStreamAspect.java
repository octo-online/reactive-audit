package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Aspect
public class OutputStreamAspect extends AbstractOutputStreamAudit
{
    @Pointcut("call(* java.io.OutputStream.close())")
    void close() {}
    @Pointcut("call(* java.io.OutputStream.flush())")
    void flush() {}
    @Pointcut("call(* java.io.OutputStream.write(..))")
    void write() {}

    @Before("(close() || flush() || write())")
    @LatencyLevel(LatencyLevel.HIGH)
    public void advice_high(JoinPoint thisJoinPoint)
    {
	    if (isLastOutputStreamWithLatency((OutputStream)thisJoinPoint.getTarget()))
		    highLatency(thisJoinPoint);
    }
}
