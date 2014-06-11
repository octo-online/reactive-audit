package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AbstractAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import static com.octo.reactive.audit.lib.Latency.LOW;

/**
 * Created by pprados on 19/05/2014.
 */
@Aspect
public class FileAspect extends AbstractAudit
{
	@Pointcut("call(* java.io.File+.createNewFile())")
	public void createNewFile()
	{
	}

	@Pointcut("call(* java.io.File+.createTempFile(..))")
	public void createTempFile()
	{
	}

	@Pointcut("call(* java.io.File+.delete(..))")
	public void delete()
	{
	}

	@Pointcut("call(* java.io.File+.exists(..))")
	public void exists()
	{
	}

	@Pointcut("call(* java.io.File+.getFreeSpace(..))")
	public void getFreeSpace()
	{
	}

	@Pointcut("call(* java.io.File+.getTotalSpace(..))")
	public void getTotalSpace()
	{
	}

	@Pointcut("call(* java.io.File+.getUsableSpace(..))")
	public void getUsableSpace()
	{
	}

	@Pointcut("call(* java.io.File+.list(..))")
	public void list()
	{
	}

	@Pointcut("call(* java.io.File+.listFiles(..))")
	public void listFiles()
	{
	}

	@Pointcut("call(* java.io.File+.mkdir*(..))")
	public void mkdir()
	{
	}

	@Pointcut("call(* java.io.File+.renameTo(..))")
	public void renameTo()
	{
	}

	@Pointcut("call(* java.io.File+.set*(..))")
	public void setX()
	{
	}

	@Before("(createNewFile() || createTempFile() || delete() || exists() " +
			        "|| getFreeSpace() || getTotalSpace() || getUsableSpace() " +
			        "|| list() || listFiles() " +
			        "|| mkdir() || renameTo() " +
			        "|| setX())")
	public void advice_low(JoinPoint thisJoinPoint)
	{
		latency(LOW, thisJoinPoint);
	}

}
