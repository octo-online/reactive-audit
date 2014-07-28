package com.octo.reactive.audit.java.util.zip;

import com.octo.reactive.audit.AbstractFileAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 6
@Aspect
public class ZipFileAudit extends AbstractFileAudit
{
	@Before("call(java.util.zip.ZipFile.new(..))")
	public void New(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
