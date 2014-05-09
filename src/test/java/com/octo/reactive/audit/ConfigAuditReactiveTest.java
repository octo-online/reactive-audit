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
}
