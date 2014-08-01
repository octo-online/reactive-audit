package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 2
@Aspect
public class ConnectionPoolDataSourceAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.sql.ConnectionPoolDataSource.getPooledConnection(..))")
	public void getPooledConnection(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
