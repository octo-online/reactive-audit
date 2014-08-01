package com.octo.reactive.audit.java.util.concurrent.locks;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest
{
	private final StampedLock c = new StampedLock();

	@Test(expected = CPUAuditReactiveException.class)
	public void readLock() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.readLock();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void readLockInterruptibly() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.readLockInterruptibly();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void writeLock() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.writeLock();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void writeLockInterruptibly() throws InterruptedException
	{
		AuditReactive.strict.commit();
		c.writeLockInterruptibly();
	}

}
