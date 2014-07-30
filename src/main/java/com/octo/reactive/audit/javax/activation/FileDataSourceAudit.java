package com.octo.reactive.audit.javax.activation;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class FileDataSourceAudit extends NetworkAudit
{
	@Before("call(javax.activation.FileDataSource.new(..))")
	public void New(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
