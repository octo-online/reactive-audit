package com.octo.reactive.audit.java.nio.file;

import com.octo.reactive.audit.FileAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;

/**
 * Created by pprados on 18/06/2014.
 */
@Aspect
public class FilesAudit extends FileAudit
{
	@Before("call(* java.nio.file.Files.copy(..))")
	public void copy(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(int java.nio.file.Files.list(..))")
	public void list(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(byte[] java.nio.file.Files.readAllBytes(..))")
	public void readAllBytes(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(java.util.List<String> java.nio.file.Files.readAllLines(..))")
	public void readAllLines(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(java.nio.file.Path java.nio.file.Files.walkFileTree(..))")
	public void walkFileTree(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}

	@Before("call(java.nio.file.Path java.nio.file.Files.write(..))")
	public void write(JoinPoint thisJoinPoint)
	{
		latency(HIGH, thisJoinPoint);
	}
}
