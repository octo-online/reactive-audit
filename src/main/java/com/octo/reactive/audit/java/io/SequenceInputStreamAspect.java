package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;

@Aspect
public class SequenceInputStreamAspect extends AbstractAudit
{
	// FIXME: new ?
	@Before("initialization(java.io.SequenceInputStream+.new(..))")
	@LatencyLevel(LatencyLevel.MEDIUM)
	public void new_(JoinPoint thisJoinPoint) throws AuditReactiveException
	{
		mediumLatency(thisJoinPoint);
	}

//	@Pointcut("initialization(* java.io.SequenceInputStream+.new(..))")
//	@LatencyLevel(LatencyLevel.HIGH)
//	public void theNew() {}
//	static void theNew(InputStream... ins) {}

//	@Before("(theNew())")
//	@LatencyLevel(LatencyLevel.MEDIUM)
//	public void advice_medium(JoinPoint thisJoinPoint)
//	{
////		if (isLastOutputStreamFromWriterWithLatency(thisJoinPoint) instanceof FileOutputStream)
////			mediumLatency(thisJoinPoint);
//	}
	//public void New(InputStream[] streams)
//	{
//		System.err.println("JE SUIS ICI AVEC "+streams);
//	}

}