package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Phaser;

public class PhaserTest
{
	private final Phaser p = new Phaser();

	@Test(expected = CPUAuditReactiveException.class)
	public void arriveAndAwaitAdvance() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		p.arriveAndAwaitAdvance();
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void awaitAdvance() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		p.awaitAdvance(1);
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void awaitAdvanceInterruptibly() throws InterruptedException, ExecutionException
	{
		AuditReactive.strict.commit();
		p.awaitAdvanceInterruptibly(1);
	}
}
