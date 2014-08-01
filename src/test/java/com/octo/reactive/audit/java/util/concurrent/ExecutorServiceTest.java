package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ExecutorServiceTest
{
	final private ExecutorService e = Executors.newFixedThreadPool(1);

	@Test(expected = CPUAuditReactiveException.class)
	public void awaitTermination() throws InterruptedException
	{
		AuditReactive.strict.commit();
		e.awaitTermination(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void invokeAny() throws InterruptedException, ExecutionException, TimeoutException
	{
		AuditReactive.strict.commit();
		e.invokeAny(new ArrayList<Callable<Object>>(), 1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void shutdownNow() throws InterruptedException
	{
		AuditReactive.strict.commit();
		e.shutdownNow();
	}
}
