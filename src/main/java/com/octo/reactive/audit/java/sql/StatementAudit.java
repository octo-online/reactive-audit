package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 21
@Aspect
public class StatementAudit extends AbstractNetworkAudit
{
	@Before("call(* java.sql.Statement.execute*(..))")
	public void execute(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(boolean java.sql.Statement.getMoreResults(..))")
	public void getMoreResults(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* java.sql.Statement.getResultSet*())")
	public void getResultSet(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
