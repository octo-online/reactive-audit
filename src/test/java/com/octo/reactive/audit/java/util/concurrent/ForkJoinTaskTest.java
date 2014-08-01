package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ForkJoinTaskTest
{
	private final ForkJoinTask e = new ForkJoinTask()
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
	};

	@Test(expected = CPUAuditReactiveException.class)
	public void get() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		e.get();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void get_to() throws InterruptedException, ExecutionException, TimeoutException
	{
		AuditReactive.strict.commit();
		e.get(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void join() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		e.join();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void quietlyInvoke() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		e.quietlyInvoke();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void quietlyJoin() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		e.quietlyJoin();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void quietlyComplete() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		e.quietlyComplete();
	}
}
