package com.octo.reactive.audit;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by pprados on 07/05/2014.
 */
public class ConfigAuditReactiveTest
{
	// TODO: completer les tests
	@Test
	public void testCurrentThread_test()
	{
		assertTrue(ConfigAuditReactive.config.isThreadNameMatch(Thread.currentThread().getName()));
	}
	@Test
	public void testCurrentThread_nothing()
	{
		ConfigAuditReactive.config.begin().threadPattern("(?!)").commit();
		assertFalse(ConfigAuditReactive.config.isThreadNameMatch(Thread.currentThread().getName()));
	}
	@Test
	public void setAllParams()
	{
		ConfigAuditReactive.config.begin()
				.log(Logger.Info)
				.throwExceptions(true)
				.threadPattern("")
				.bootStrapDelay(10)
				.commit();
		assertEquals(Logger.Info,ConfigAuditReactive.config.getLogLevel());
		assertEquals(true,ConfigAuditReactive.config.isThrow());
		assertEquals("",ConfigAuditReactive.config.getThreadPattern());
		assertEquals(10,ConfigAuditReactive.config.getBootstrapDelay());
		ConfigAuditReactive.config.begin()
				.log(Logger.Warn)
				.throwExceptions(false)
				.threadPattern("abc")
				.bootStrapDelay(0)
				.commit();
		assertEquals(Logger.Warn,ConfigAuditReactive.config.getLogLevel());
		assertEquals(false,ConfigAuditReactive.config.isThrow());
		assertEquals("abc",ConfigAuditReactive.config.getThreadPattern());
		assertEquals(0,ConfigAuditReactive.config.getBootstrapDelay());
	}
	@Test
	public void logIfNew() throws InterruptedException
	{
		ConfigAuditReactive.logOnly.commit();
		boolean[] log=new boolean[1];
		class DelegateLogger implements Logger.DelegateLogger
		{
			public void log(int level, String msg)
			{
				log[0]=true;
			}
		}
		ctx1();   // Log
		assertTrue(log[0]);
		log[0]=false;
		ctx2();   // Log
		assertTrue(log[0]);
		log[0]=false;
		ctx1();   // no-Log
		assertFalse(log[0]);
		log[0]=false;
		ctx3();   // Log
		assertTrue(log[0]);
		log[0]=false;
		ctx4();   // Log
		assertTrue(log[0]);
		log[0]=false;
		ctx3();   // no-Log
		assertFalse(log[0]);
		log[0]=false;
		ctx4();   // no-Log
		assertFalse(log[0]);
		log[0]=false;

	}
	private void ctx1() throws InterruptedException
	{
		Thread.sleep(1);
	}
	private void ctx2() throws InterruptedException
	{
		Thread.sleep(1);
	}
	private void ctx3() throws InterruptedException
	{
		ctx1();
	}
	private void ctx4() throws InterruptedException
	{
		ctx1();
		ctx2();
	}
}
