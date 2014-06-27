package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.FileAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.LOW;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class FileAspect extends FileAudit
{
	@Before("call(* java.io.File+.createNewFile())")
	public void createNewFile(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.createTempFile(..))")
	public void createTempFile(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.delete(..))")
	public void delete(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.exists(..))")
	public void exists(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.getFreeSpace(..))")
	public void getFreeSpace(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.getTotalSpace(..))")
	public void getTotalSpace(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.getUsableSpace(..))")
	public void getUsableSpace(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.list(..))")
	public void list(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.listFiles(..))")
	public void listFiles(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.mkdir*(..))")
	public void mkdir(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.renameTo(..))")
	public void renameTo(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

	@Before("call(* java.io.File+.set*(..))")
	public void setX(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

}
