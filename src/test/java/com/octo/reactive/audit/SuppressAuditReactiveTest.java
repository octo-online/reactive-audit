package com.octo.reactive.audit;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by pprados on 09/05/2014.
 */
public class SuppressAuditReactiveTest
{
	@Test
	@SuppressAuditReactive
	public void directCall()
	{
		 assertTrue(ConfigAuditReactive.config.isSuppressAudit());
	}
	@Test
	@SuppressAuditReactive
	public void indirectCall()
	{
		Runnable r=new Runnable()
		{
			@Override
			public void run()
			{
				assertTrue(ConfigAuditReactive.config.isSuppressAudit());
				assertEquals(1,ConfigAuditReactive.config.getSuppress());
			}
		};
		r.run();
		assertTrue(ConfigAuditReactive.config.isSuppressAudit());
		assertEquals(1,ConfigAuditReactive.config.getSuppress());
	}
	@Test
	@SuppressAuditReactive
	public void doubleCall()
	{
		Runnable r=new Runnable()
		{
			@SuppressAuditReactive
			@Override
			public void run()
			{
				assertTrue(ConfigAuditReactive.config.isSuppressAudit());
				assertEquals(2,ConfigAuditReactive.config.getSuppress());
			}
		};
		r.run();
		assertEquals(1,ConfigAuditReactive.config.getSuppress());
	}
}
