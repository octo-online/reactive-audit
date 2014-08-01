package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

public class SemaphoreTest
{
	private final Semaphore p = new Semaphore(1);

	@Test(expected = CPUAuditReactiveException.class)
	public void acquire() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		p.acquire();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void acquireUninterruptibly() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		p.acquireUninterruptibly();
	}

}
