package com.octo.reactive.audit.java.sql;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class ResultSetAudit extends NetworkAudit
{
	@Before("call(boolean java.sql.ResultSet.absolute(int))")
	public void absolute(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(void java.sql.ResultSet.afterLast())")
	public void afterLast(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(void java.sql.ResultSet.beforeFirst())")
	public void beforeFirst(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(void java.sql.ResultSet.deleteRow())")
	public void deleteRow(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(boolean java.sql.ResultSet.first())")
	public void first(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(boolean java.sql.ResultSet.last())")
	public void last(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(boolean java.sql.ResultSet.next())")
	public void next(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(boolean java.sql.ResultSet.previous())")
	public void previous(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(void java.sql.ResultSet.refreshRow())")
	public void refreshRow(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(boolean java.sql.ResultSet.relative(int ))")
	public void relative(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
