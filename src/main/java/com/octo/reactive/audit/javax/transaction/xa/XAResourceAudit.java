package com.octo.reactive.audit.javax.transaction.xa;

import com.octo.reactive.audit.FileAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class XAResourceAudit extends FileAudit
{
	@Before("call(* javax.transaction.XAResource.commit(..))")
	public void commit(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* javax.transaction.XAResource.rollback(..))")
	public void rollback(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
