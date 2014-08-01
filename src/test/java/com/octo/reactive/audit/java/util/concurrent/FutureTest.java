package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTest
{
	private final Future<Object> f = new Future<Object>()
	{

		@Override
		public boolean cancel(boolean mayInterruptIfRunning)
		{
			return false;
		}

		@Override
		public boolean isCancelled()
		{
			return false;
		}

		@Override
		public boolean isDone()
		{
			return false;
		}

		@Override
		public Object get() throws InterruptedException, ExecutionException
		{
			return null;
		}

		@Override
		public Object get(long timeout, TimeUnit unit)
				throws InterruptedException, ExecutionException, TimeoutException
		{
			return null;
		}
	};

	@Test(expected = CPUAuditReactiveException.class)
	public void get() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		f.get();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void get_to() throws InterruptedException, ExecutionException, TimeoutException
	{
		AuditReactive.strict.commit();
		f.get(1, TimeUnit.MILLISECONDS);
	}
}
