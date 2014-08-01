package com.octo.reactive.audit.javax.sql.rowset.spi;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 3
@Aspect
public class TransactionalWriterAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.sql.rowset.spi.TransactionalWriter.commit())")
	public void commit(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* javax.sql.rowset.spi.TransactionalWriter.rollback(..))")
	public void rollback(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
