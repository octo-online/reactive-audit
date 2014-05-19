package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.LatencyLevel;
import com.octo.reactive.audit.java.io.AbstractOutputStreamAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.OutputStream;
import java.io.Writer;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class ObjectAspect extends AbstractAudit
{
	@Before("call(* java.lang.Object.wait(..))")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(LatencyLevel.HIGH,thisJoinPoint);
	}
}
