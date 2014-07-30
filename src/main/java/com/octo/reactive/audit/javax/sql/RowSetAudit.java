package com.octo.reactive.audit.javax.sql;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class RowSetAudit extends NetworkAudit
{
	@Before("call(* javax.sql.RowSet.execute())")
	public void execute(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

}
