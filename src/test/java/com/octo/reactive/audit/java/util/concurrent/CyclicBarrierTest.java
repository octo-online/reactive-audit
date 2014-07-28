package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierTest
{
	@Test(expected = CPUAuditReactiveException.class)
	public void await() throws InterruptedException, BrokenBarrierException
	{
		AuditReactive.strict.commit();
		CyclicBarrier c = new CyclicBarrier(1);
		c.await();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void await_to() throws InterruptedException, BrokenBarrierException, TimeoutException
	{
		AuditReactive.strict.commit();
		CyclicBarrier c = new CyclicBarrier(1);
		c.await(1, TimeUnit.MILLISECONDS);
	}
}
