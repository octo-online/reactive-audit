package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.FileAuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.io.InputStream;

@Aspect
public class FileInputStreamAspect extends AbstractInputStreamAudit
{
	@Before("call(java.io.FileInputStream+.new(..))")
	public void new_(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		latency(LatencyLevel.LOW,thisJoinPoint);
	}
}