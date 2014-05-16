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
				.log(ConfigAuditReactive.Info)
				.throwExceptions(true)
				.threadPattern("")
				.bootStrapDelay(10)
				.commit();
		assertEquals(ConfigAuditReactive.Info,ConfigAuditReactive.config.getLogLevel());
		assertEquals(true,ConfigAuditReactive.config.isThrow());
		assertEquals("",ConfigAuditReactive.config.getThreadPattern());
		assertEquals(10,ConfigAuditReactive.config.getBootstrapDelay());
		ConfigAuditReactive.config.begin()
				.log(ConfigAuditReactive.Warn)
				.throwExceptions(false)
				.threadPattern("abc")
				.bootStrapDelay(0)
				.commit();
		assertEquals(ConfigAuditReactive.Warn,ConfigAuditReactive.config.getLogLevel());
		assertEquals(false,ConfigAuditReactive.config.isThrow());
		assertEquals("abc",ConfigAuditReactive.config.getThreadPattern());
		assertEquals(0,ConfigAuditReactive.config.getBootstrapDelay());
	}
}
