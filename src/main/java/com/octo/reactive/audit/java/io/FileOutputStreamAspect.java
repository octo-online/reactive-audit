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
    @Pointcut("call(java.io.FileOutputStream.new(..))")
    void new_() {}
    @Pointcut("call(* java.io.OutputStream.close())")
    void close() {}
    @Pointcut("call(* java.io.OutputStream.flush())")
    void flush() {}
    @Pointcut("call(* java.io.OutputStream.write(..))")
    void write() {}

	@Before("(new_())")
    @SuppressAjWarnings({"adviceDidNotMatch"})
    @LatencyLevel(LatencyLevel.MEDIUM)
    public void advice_medium(JoinPoint thisJoinPoint)
    {
        mediumLatency(thisJoinPoint);
    }
    @Before("(close() || flush() || write())" +
		    "&& target(java.io.FileOutputStream)")
    @SuppressAjWarnings({"adviceDidNotMatch"})
    @LatencyLevel(LatencyLevel.HIGH)
    public void advice_high(JoinPoint thisJoinPoint)
    {
	    highLatency(thisJoinPoint);
    }
}
