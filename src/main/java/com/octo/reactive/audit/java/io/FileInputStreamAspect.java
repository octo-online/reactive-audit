package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.SuppressAjWarnings;

@Aspect
public class FileInputStreamAspect extends AbstractAudit
{
    @Pointcut("call(* java.io.InputStream.available())")
    public void available() { }
    @Pointcut("call(* java.io.InputStream.read(..))")
    public void read(){ }
    @Pointcut("call(* java.io.InputStream.skip(long))")
    public void skip() { }
	@Pointcut("call(* java.io.InputStream.close())")
    public void close() { }

	@Before("call(java.io.FileInputStream.new(..))")
	@SuppressAjWarnings({"adviceDidNotMatch"})
	@LatencyLevel(LatencyLevel.MEDIUM)
	public void new_(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		mediumLatency(thisJoinPoint);
	}

	@Before("(available() || skip())" +
		    "&& target(java.io.FileInputStream)")
    @SuppressAjWarnings({"adviceDidNotMatch"})
    @LatencyLevel(LatencyLevel.LOW)
    public void advice_low(JoinPoint thisJoinPoint)
    {
	    lowLatency(thisJoinPoint);
    }

    @Before("(close())" +
		    "&& target(java.io.FileInputStream)")
    @SuppressAjWarnings({"adviceDidNotMatch"})
    @LatencyLevel(LatencyLevel.MEDIUM)
    public void advice_medium(JoinPoint thisJoinPoint)
    {
        mediumLatency(thisJoinPoint);
    }

    @Before("(read())" +
		    "&& target(java.io.FileInputStream)")
    @SuppressAjWarnings({"adviceDidNotMatch"})
    @LatencyLevel(LatencyLevel.HIGH)
    public void advice_high(JoinPoint thisJoinPoint)
    {
	    highLatency(thisJoinPoint);
    }
}