package com.octo.reactive.audit.javax.sql.rowset;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 3
@Aspect
public class JdbcRowSetAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.sql.rowset.JdbcRowSet.commit())")
	public void commit(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* javax.sql.rowset.JdbcRowSet.rollback(..))")
	public void rollback(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
