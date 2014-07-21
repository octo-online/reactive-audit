package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class SQLDataAudit extends NetworkAudit
{
	@Before("call(void java.sql.SQLData.readSQL(..))")
	public void readSQL(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(void java.sql.SQLData.writeSQL(..))")
	public void writeSQL(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
