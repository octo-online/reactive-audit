package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.TimeUnit;

public class CompletionServiceTest
{
	Executor e = new Executor()
	{

		@Override
		public void execute(Runnable command)
		{

		}
	};

	@Test(expected = CPUAuditReactiveException.class)
	public void poll() throws InterruptedException
	{
		AuditReactive.strict.commit();
		CompletionService<Object> q = new ExecutorCompletionService<Object>(e);
		q.poll(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void take() throws InterruptedException
	{
		AuditReactive.strict.commit();
		CompletionService<Object> q = new ExecutorCompletionService<Object>(e);
		q.take();
	}
}
