package com.octo.reactive.audit.java.util.concurrent.locks;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class ReadWriteLockTest
{
	ReadWriteLock c = new ReadWriteLock()
	{

		@Override
		public Lock readLock()
		{
			return null;
		}

		@Override
		public Lock writeLock()
		{
			return null;
		}
	};

	@Test(expected = CPUAuditReactiveException.class)
	public void readLock() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.readLock();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void writeLock() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.writeLock();
	}

}
