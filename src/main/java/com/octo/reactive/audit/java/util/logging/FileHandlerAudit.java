package com.octo.reactive.audit.java.util.logging;

import com.octo.reactive.audit.FileAudit;
import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.io.InputStream;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class FileHandlerAudit extends FileAudit
{
	@Before("call(java.util.logging.FileHandler.new(..))")
	public void New(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
