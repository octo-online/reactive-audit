package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.SuppressAjWarnings;

import java.io.OutputStream;

@Aspect
public class FileOutputStreamAspect extends AbstractOutputStreamAudit
{
	@Before("call(java.io.FileOutputStream+.new(..))")
    public void new_(JoinPoint thisJoinPoint)
    {
        latency(LatencyLevel.LOW,thisJoinPoint);
    }
}
