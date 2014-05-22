package com.octo.reactive.audit;

import org.junit.Test;

import java.io.File;

import static com.octo.reactive.audit.Logger.Level;
import static com.octo.reactive.audit.Logger.Level.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by pprados on 07/05/2014.
 */
public class ConfigAuditReactiveTest
{

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
				.log(Info)
				.throwExceptions(true)
				.threadPattern("")
				.bootStrapDelay(10)
				.commit();
		assertEquals(Info,ConfigAuditReactive.config.getLogLevel());
		assertEquals(true,ConfigAuditReactive.config.isThrow());
		assertEquals("",ConfigAuditReactive.config.getThreadPattern());
		assertEquals(10,ConfigAuditReactive.config.getBootstrapDelay());
		ConfigAuditReactive.config.begin()
				.log(Warn)
				.throwExceptions(false)
				.threadPattern("abc")
				.bootStrapDelay(0)
				.commit();
		assertEquals(Warn,ConfigAuditReactive.config.getLogLevel());
		assertEquals(false,ConfigAuditReactive.config.isThrow());
		assertEquals("abc",ConfigAuditReactive.config.getThreadPattern());
		assertEquals(0,ConfigAuditReactive.config.getBootstrapDelay());
	}
	@Test(expected = IllegalArgumentException.class)
	public void lockTransaction()
	{
		ConfigAuditReactive.config.begin()
				.seal()
				.log(Warn);
	}

	@Test
	@SuppressAuditReactive // For accept join
	public void logIfNewThread() throws InterruptedException
	{
		ConfigAuditReactive.config.begin()
				.threadPattern(".*")
				.log(Warn)
				.throwExceptions(false)
				.commit();
		int[] log=new int[1];
		ConfigAuditReactive.config.logger.delegate = new Logger.DelegateLogger()
		{
			@Override
			public void log(Level level, Object msg)
			{
				if (level!=Debug)
					++log[0];
			}
		};
		Thread t;

		log[0] = 0;
		// FIXME: ecriture java8 rejeté par aspectj 8.0
		Runnable rctx1 = new Runnable()
		{
			@Override
			public void run()
			{
				latencyCall1();
			}
		};
		t = new Thread(rctx1);
		t.start();
		t.join();
		assertEquals(1,log[0]);

		log[0]=0;
		Runnable rctx2 = new Runnable()
		{
			@Override
			public void run()
			{
				latencyCall2();
			}
		};
		t = new Thread(rctx2);
		t.start();
		t.join();
		assertEquals(1,log[0]);

		log[0]=0;
		t = new Thread(rctx1);
		t.start();
		t.join();
		assertEquals(0,log[0]);

		log[0]=0;
		Runnable rctx3 = new Runnable()
		{
			@Override
			public void run()
			{
				latencyCall3();
			}
		};
		t = new Thread(rctx3);
		t.start();
		t.join();
		assertEquals(1,log[0]);

		log[0]=0;
		Runnable rctx4 = new Runnable()
		{
			@Override
			public void run()
			{
				latencyCall4();
			}
		};
		t = new Thread(rctx4);
		t.start();
		t.join();
		assertEquals(2,log[0]);

		log[0]=0;
		t = new Thread(rctx3);
		t.start();
		t.join();
		assertEquals(0,log[0]);

		log[0]=0;
		t = new Thread(rctx4);
		t.start();
		t.join();
		assertEquals(0,log[0]);
	}

	@Test
	public void logIfNewLoop()
	{
		ConfigAuditReactive.config.begin()
				.threadPattern(".*")
				.log(Warn)
				.throwExceptions(false)
				.commit();
		int[] log = new int[1];
		ConfigAuditReactive.config.logger.delegate = new Logger.DelegateLogger()
		{
			@Override
			public void log(Level level, Object msg)
			{
				if (level!=Debug)
					++log[0];
			}
		};

		for (int i = 0; i < 10; ++i)
		{
			log[0] = 0;
			latencyCall1();
			if (i == 0) assertEquals(1,log[0]);
			else assertEquals(0,log[0]);

		}
	}

	private void latencyCall1()
	{
		new File("/").getFreeSpace();
	}

	private void latencyCall2()
	{
		new File("/").getFreeSpace();
	}

	private void latencyCall3()
	{
		latencyCall1();
	}

	private void latencyCall4()
	{
		latencyCall1();
		latencyCall2();
	}
}
