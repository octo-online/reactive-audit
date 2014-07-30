package com.octo.reactive.audit.javax.xml.ws;

import com.octo.reactive.audit.FileAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class EndPointAudit extends FileAudit
{
	@Before("call(* javax.xml.ws.EndPoint.publish(..))")
	public void publish(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
