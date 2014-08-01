package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest
{
	private final CompletableFuture<Object> f = new CompletableFuture<Object>();

	@Test(expected = CPUAuditReactiveException.class)
	public void get() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		f.join();
	}

}
