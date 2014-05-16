package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.LatencyLevel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.FileInputStream;

@Aspect
public class ConsoleAspect extends AbstractAudit
{
	@Pointcut("call(* java.io.Console.readLine(..))")
	public void readLine()
	{
	}

	@Pointcut("call(* java.io.Console.readPassword(..))")
	public void readPassword()
	{
	}


	@Before("(readLine() || readPassword())")
	public void advice_high(JoinPoint thisJoinPoint)
	{
		latency(LatencyLevel.HIGH,thisJoinPoint);
	}

}