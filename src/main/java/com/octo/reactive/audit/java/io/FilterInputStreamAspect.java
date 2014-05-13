package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.SuppressAjWarnings;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;

@Aspect
public class FilterInputStreamAspect extends AbstractAudit
{
	static final Field fieldIn;

	static
	{
		try
		{
			fieldIn=FilterInputStream.class.getDeclaredField("in");
			fieldIn.setAccessible(true);
		}
		catch (NoSuchFieldException e)
		{
			throw new Error(e);
		}
	}
	private InputStream getIn(JoinPoint thisJoinPoint)
	{
		InputStream in=(FilterInputStream)thisJoinPoint.getTarget();
		while (in instanceof FilterInputStream)
		{
			try
			{
				FilterInputStream filter=(FilterInputStream)in;
				in=(InputStream)fieldIn.get(filter);
			}
			catch (IllegalAccessException e)
			{
				throw new Error(e);
			}
		}
		return in;
	}

	@Pointcut("call(* java.io.InputStream.available())")
	public void available()
	{
	}

	@Pointcut("call(* java.io.InputStream.read(..))")
	public void read()
	{
	}

	@Pointcut("call(* java.io.InputStream.skip(long))")
	public void skip()
	{
	}

	@Pointcut("call(* java.io.InputStream.close())")
	public void close()
	{
	}

	@Before("(available() || skip())" +
			        "&& target(java.io.FilterInputStream)")
	@LatencyLevel(LatencyLevel.LOW)
	public void advice_low(JoinPoint thisJoinPoint)
	{
		if (getIn(thisJoinPoint) instanceof FileInputStream)
		    lowLatency(thisJoinPoint);
    }

    @Before("(close())" +
		    "&& target(java.io.FilterInputStream)")
    @LatencyLevel(LatencyLevel.MEDIUM)
    public void advice_medium(JoinPoint thisJoinPoint)
    {
	    if (getIn(thisJoinPoint) instanceof FileInputStream)
	        mediumLatency(thisJoinPoint);
    }

    @Before("(read())" +
		    "&& target(java.io.FilterInputStream)")
    @LatencyLevel(LatencyLevel.HIGH)
    public void advice_high(JoinPoint thisJoinPoint)
    {
	    if (getIn(thisJoinPoint) instanceof FileInputStream)
		    highLatency(thisJoinPoint);
    }

}