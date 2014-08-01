package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolTest
{
	private final ForkJoinPool e = new ForkJoinPool(1);

	@Test(expected = CPUAuditReactiveException.class)
	public void awaitQuiescence() throws InterruptedException
	{
		AuditReactive.strict.commit();
		e.awaitQuiescence(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void awaitTermination() throws InterruptedException
	{
		AuditReactive.strict.commit();
		e.awaitTermination(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void invoke() throws InterruptedException
	{
		AuditReactive.strict.commit();
		e.invoke(new ForkJoinTask<Object>()
		{
			@Override
			public Object getRawResult()
			{
				return null;
			}

			@Override
			protected void setRawResult(Object value)
			{

			}

			@Override
			protected boolean exec()
			{
				return false;
			}
		});
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void managedBlock() throws InterruptedException
	{
		AuditReactive.strict.commit();
		ForkJoinPool.managedBlock(new ForkJoinPool.ManagedBlocker()
		{
			@Override
			public boolean block() throws InterruptedException
			{
				return false;
			}

			@Override
			public boolean isReleasable()
			{
				return false;
			}
		});
	}
}
