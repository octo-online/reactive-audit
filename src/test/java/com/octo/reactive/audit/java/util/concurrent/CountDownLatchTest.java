package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest
{
	@Test(expected = CPUAuditReactiveException.class)
	public void await() throws InterruptedException
	{
		AuditReactive.strict.commit();
		CountDownLatch c = new CountDownLatch(1);
		c.await();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void await_to() throws InterruptedException
	{
		AuditReactive.strict.commit();
		CountDownLatch c = new CountDownLatch(1);
		c.await(1, TimeUnit.MILLISECONDS);
	}
}
