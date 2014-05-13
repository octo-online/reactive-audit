package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class BufferedReaderAspect extends AbstractAudit
{
	@Pointcut("call(java.io.BufferedReader.new(..))")
	public void ctr(){
		}
	@Pointcut("initialization(java.io.BufferedReader.new(..))")
	public void init(){
		}

	@After("(ctr() || init())")
//	@Before("call(java.io.BufferedReader.new(..))")
	@LatencyLevel(LatencyLevel.MEDIUM)
	public void new_(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		System.err.println("JE SUIS ICI "+thisJoinPoint.toLongString());
		mediumLatency(thisJoinPoint);
	}

}