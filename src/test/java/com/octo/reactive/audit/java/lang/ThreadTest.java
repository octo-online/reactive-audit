package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;

public class ThreadTest
{
	@Test(expected = AuditReactiveException.class)
	public void join() throws InterruptedException
	{
		AuditReactive.strict.commit();
		Thread thread = new Thread();
		thread.join();
	}

	@Test(expected = AuditReactiveException.class)
	public void sleep() throws InterruptedException
	{
		AuditReactive.strict.commit();
		Thread.sleep(1);
	}
}
