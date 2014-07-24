package com.octo.reactive.audit.javax.rmi;

import com.octo.reactive.audit.NetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class PortableRemoteObjectAudit extends NetworkAudit
{
	@Before("call(* javax.rmi.PortableRemoteObject.connect(..))")
	public void connect(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

}
