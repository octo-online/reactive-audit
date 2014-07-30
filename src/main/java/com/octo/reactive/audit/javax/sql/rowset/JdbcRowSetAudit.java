package com.octo.reactive.audit.javax.sql.rowset;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class JdbcRowSetAudit extends NetworkAudit
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
