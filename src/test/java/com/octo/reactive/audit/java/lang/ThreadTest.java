package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.annotation.AuditReactiveException;
import org.junit.Test;

/**
 * Created by pprados on 19/05/2014.
 */
public class ThreadTest
{
	@Test(expected=AuditReactiveException.class)
	public void join() throws InterruptedException
	{
		ConfigAuditReactive.strict.commit();
		Thread thread=new Thread();
		thread.join();
	}
	@Test(expected=AuditReactiveException.class)
	public void sleep() throws InterruptedException
	{
		ConfigAuditReactive.strict.commit();
		Thread.sleep(1);
	}
}
