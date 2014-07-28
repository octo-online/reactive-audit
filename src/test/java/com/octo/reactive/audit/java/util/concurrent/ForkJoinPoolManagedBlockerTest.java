package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolManagedBlockerTest
{
	@Test(expected = CPUAuditReactiveException.class)
	public void awaitTermination() throws InterruptedException
	{
		AuditReactive.strict.commit();
		ForkJoinPool.ManagedBlocker m = new ForkJoinPool.ManagedBlocker()
		{
			@Override
			public boolean block() throws InterruptedException
			{
				return false;
			}

			@Override
			public boolean isReleasable()
			{
				return false;
			}
		};
		m.block();
	}
}
