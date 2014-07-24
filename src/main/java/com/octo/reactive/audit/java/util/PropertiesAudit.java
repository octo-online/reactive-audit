package com.octo.reactive.audit.java.util;

import com.octo.reactive.audit.FileAudit;
import com.octo.reactive.audit.java.io.FileTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;
import java.io.Reader;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class PropertiesAudit extends FileAudit
{
	@Before("call(* java.util.Properties.load(java.io.InputStream)) && args(in)")
	public void advice_high(JoinPoint thisJoinPoint, InputStream in)
	{
		if (FileTools.isLastInputStreamWithLatency(in) != 0)
			latency(HIGH, thisJoinPoint);
	}

	@Before("call(* java.util.Properties.load(java.io.Reader)) && args(in)")
	public void advice_high(JoinPoint thisJoinPoint, Reader in)
	{
		if (FileTools.isLastReaderWithLatency(in) != 0)
			latency(HIGH, thisJoinPoint);
	}
}
