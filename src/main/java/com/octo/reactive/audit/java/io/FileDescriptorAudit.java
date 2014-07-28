package com.octo.reactive.audit.java.io;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.OutputStream;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 2
@Aspect
public class FileDescriptorAudit extends AbstractOutputStreamAudit
{
	@Before("call(* java.io.FileDescriptor.*())") // FIXME
	public void advice_medium(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint, (OutputStream) thisJoinPoint.getTarget());
	}

}
