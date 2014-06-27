package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.FileAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class ConsoleAspect extends FileAudit
{
	@Before("call(* java.io.Console.readLine(..))")
	public void readLine(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(* java.io.Console.readPassword(..))")
	public void readPassword(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
