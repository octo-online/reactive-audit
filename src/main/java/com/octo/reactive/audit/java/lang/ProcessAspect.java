package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.LatencyLevel;
import com.octo.reactive.audit.java.io.AbstractOutputStreamAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.OutputStream;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class ProcessAspect extends AbstractAudit
{
	@Before("call(* java.lang.Process.waitFor(..))")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(LatencyLevel.HIGH,thisJoinPoint);
	}

}
