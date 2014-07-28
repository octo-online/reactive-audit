package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

// Nb methods: 4
@Aspect
public class PreparedStatementAudit extends AbstractNetworkAudit
{
	@Before("call(* java.sql.PreparedStatement.execute*(..))")
	public void execute(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
