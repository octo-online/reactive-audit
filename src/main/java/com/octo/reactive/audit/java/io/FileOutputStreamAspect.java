package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.SuppressAjWarnings;

@Aspect
public class FileOutputStreamAspect extends AbstractAudit
{
    @Pointcut("initialization(java.io.FileOutputStream+.new(..))")
    void new_() {}
	@Before("(new_())")
    @LatencyLevel(LatencyLevel.MEDIUM)
    public void advice_medium(JoinPoint thisJoinPoint)
    {
        mediumLatency(thisJoinPoint);
    }
}
