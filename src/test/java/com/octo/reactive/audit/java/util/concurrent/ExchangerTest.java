package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExchangerTest
{
	@Test(expected = CPUAuditReactiveException.class)
	public void exchange() throws InterruptedException, BrokenBarrierException
	{
		AuditReactive.strict.commit();
		Exchanger c = new Exchanger<Object>();
		c.exchange("");
	}

	@Test(expected = CPUAuditReactiveException.class)
	public void exchange_to() throws InterruptedException, BrokenBarrierException, TimeoutException
	{
		AuditReactive.strict.commit();
		Exchanger c = new Exchanger<Object>();
		c.exchange("", 1, TimeUnit.MILLISECONDS);
	}
}
