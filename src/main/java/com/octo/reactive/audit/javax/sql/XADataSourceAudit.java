package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 2
@Aspect
public class XADataSourceAudit extends AbstractNetworkAudit
{
	@Before("call(* javax.sql.XADataSource.getXAConnection(..))")
	public void getXAConnection(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
