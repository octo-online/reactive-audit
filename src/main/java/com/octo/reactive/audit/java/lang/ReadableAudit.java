package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.java.io.AbstractInputStreamAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 1
@Aspect
public class ReadableAudit extends AbstractInputStreamAudit
{
	@Before("call(int java.lang.Readable.read(java.nio.CharBuffer))")
	public void read(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
