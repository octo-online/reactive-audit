package com.octo.reactive.audit.java.util.concurrent.locks;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class LockTest
{
	Lock c = new Lock()
	{

		@Override
		public void lock()
		{

		}

		@Override
		public void lockInterruptibly() throws InterruptedException
		{

		}

		@Override
		public boolean tryLock()
		{
			return false;
		}

		@Override
		public boolean tryLock(long l, TimeUnit timeUnit) throws InterruptedException
		{
			return false;
		}

		@Override
		public void unlock()
		{

		}

		@Override
		public Condition newCondition()
		{
			return null;
		}
	};

	@Test(expected = CPUAuditReactiveException.class)
	public void lock() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.lock();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void lockInterruptibly() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.lockInterruptibly();
		;
	}

}
