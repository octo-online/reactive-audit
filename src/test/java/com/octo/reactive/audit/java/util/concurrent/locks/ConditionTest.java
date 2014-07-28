package com.octo.reactive.audit.java.util.concurrent.locks;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class ConditionTest
{
	Condition c = new Condition()
	{

		@Override
		public void await() throws InterruptedException
		{

		}

		@Override
		public void awaitUninterruptibly()
		{

		}

		@Override
		public long awaitNanos(long l) throws InterruptedException
		{
			return 0;
		}

		@Override
		public boolean await(long l, TimeUnit timeUnit) throws InterruptedException
		{
			return false;
		}

		@Override
		public boolean awaitUntil(Date date) throws InterruptedException
		{
			return false;
		}

		@Override
		public void signal()
		{

		}

		@Override
		public void signalAll()
		{

		}
	};

	@Test(expected = CPUAuditReactiveException.class)
	public void await() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.await();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void await_to() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.await(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void awaitNanos() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.awaitNanos(1);
	}
}
