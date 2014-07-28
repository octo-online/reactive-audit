package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 3
@Aspect
public class DriverManagerAudit extends AbstractNetworkAudit
{
	@Before("call(static * java.sql.DriverManager.getConnection(..))")
	public void getConnection(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}
}
