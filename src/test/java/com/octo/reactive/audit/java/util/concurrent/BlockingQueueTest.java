package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTest
{
	@Test(expected = CPUAuditReactiveException.class)
	public void offer() throws InterruptedException
	{
		AuditReactive.strict.commit();
		BlockingQueue<Object> q = new LinkedBlockingDeque<>();
		q.offer("", 1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void poll() throws InterruptedException
	{
		AuditReactive.strict.commit();
		BlockingQueue<Object> q = new LinkedBlockingDeque<>();
		q.poll(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void put() throws InterruptedException
	{
		AuditReactive.strict.commit();
		BlockingQueue<Object> q = new LinkedBlockingDeque<>();
		q.put("");
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void take() throws InterruptedException
	{
		AuditReactive.strict.commit();
		BlockingQueue<Object> q = new LinkedBlockingDeque<>();
		q.take();
	}
}
