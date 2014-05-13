package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.SuppressAjWarnings;

import java.io.*;
import java.lang.reflect.Field;
// Analyse en profondeur de la chaine
@Aspect
public class FilterOutputStreamAspect extends AbstractAudit
{
	static final Field fieldOut;

	static
	{
		try
		{
			fieldOut = FilterOutputStream.class.getDeclaredField("out");
			fieldOut.setAccessible(true);
		}
		catch (NoSuchFieldException e)
		{
			throw new Error(e);
		}
	}

	private OutputStream getOut(JoinPoint thisJoinPoint)
	{
		OutputStream out = (FilterOutputStream) thisJoinPoint.getTarget();
		while (out instanceof FilterOutputStream)
		{
			try
			{
				FilterOutputStream filter = (FilterOutputStream) out;
				out = (OutputStream) fieldOut.get(filter);
			}
			catch (IllegalAccessException e)
			{
				throw new Error(e);
			}
		}
		return out;
	}

	@Pointcut("call(* java.io.OutputStream.close())")
	void close()
	{
	}

	@Pointcut("call(* java.io.OutputStream.flush())")
	void flush()
	{
	}

	@Pointcut("call(* java.io.OutputStream.write(..))")
	void write()
	{
	}

    @Before("(close() || flush() || write())" +
		    "&& target(java.io.FilterOutputStream)")
    @LatencyLevel(LatencyLevel.HIGH)
    public void advice_high(JoinPoint thisJoinPoint)
    {
	    if (getOut(thisJoinPoint) instanceof FileOutputStream)
		    highLatency(thisJoinPoint);
    }
}
