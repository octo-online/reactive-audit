package com.octo.reactive.audit;

import org.junit.Test;

import java.io.File;

import static com.octo.reactive.audit.Logger.Level;
import static com.octo.reactive.audit.Logger.Level.Warn;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by pprados on 07/05/2014.
 */
public class ConfigAuditReactiveTest
{
	/*
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
	@Test(expected = IllegalArgumentException.class)
	public void lockTransaction()
	{
		ConfigAuditReactive.config.begin()
				.seal()
				.log(Logger.Warn);
	}
*/
	@Test
	@SuppressAuditReactive // For accept join
	public void logIfNewThread() throws InterruptedException
	{
		ConfigAuditReactive.config.begin()
				.threadPattern(".*")
				.log(Warn)
				.throwExceptions(false)
				.commit();
		boolean[] log=new boolean[1];
		ConfigAuditReactive.config.logger.delegate = new Logger.DelegateLogger()
		{
			@Override
			public void log(Level level, Object msg)
			{
				log[0]=true;
			}
		};
		Thread t;

		log[0] = false;
		// FIXME: ecriture java8 rejeté par aspectj 8.0
		Runnable rctx1 = new Runnable()
		{
			@Override
			public void run()
			{
				latenceCall1();
			}
		};
//		Runnable rctx1=() -> latenceCall1();
		t = new Thread(rctx1);
		t.start();
		t.join();
		assertTrue(log[0]);

		log[0]=false;
		Runnable rctx2 = new Runnable()
		{
			@Override
			public void run()
			{
				latenceCall2();
			}
		};
		t = new Thread(rctx2);
		t.start();
		t.join();
		assertTrue(log[0]);

		log[0]=false;
		t = new Thread(rctx1);
		t.start();
		t.join();
		assertFalse(log[0]);

		log[0]=false;
		Runnable rctx3 = new Runnable()
		{
			@Override
			public void run()
			{
				latenceCall3();
			}
		};
		t = new Thread(rctx3);
		t.start();
		t.join();
		assertTrue(log[0]);

		log[0]=false;
		Runnable rctx4 = new Runnable()
		{
			@Override
			public void run()
			{
				latenceCall4();
			}
		};
		t = new Thread(rctx4);
		t.start();
		t.join();
		assertTrue(log[0]);

		log[0]=false;
		t = new Thread(rctx3);
		t.start();
		t.join();
		assertFalse(log[0]);

		log[0]=false;
		t = new Thread(rctx4);
		t.start();
		t.join();
		assertFalse(log[0]);
	}

	@Test
	public void logIfNewLoop()
	{
		ConfigAuditReactive.config.begin()
				.threadPattern(".*")
				.log(Warn)
				.throwExceptions(false)
				.commit();
		boolean[] log = new boolean[1];
		ConfigAuditReactive.config.logger.delegate = new Logger.DelegateLogger()
		{
			@Override
			public void log(Level level, Object msg)
			{
				log[0] = true;
			}
		};

		for (int i = 0; i < 10; ++i)
		{
			log[0] = false;
			latenceCall1();
			if (i == 0) assertTrue(log[0]);
			else assertFalse(log[0]);

		}
	}

	private void latenceCall1()
	{
		new File("/").getFreeSpace();
	}

	private void latenceCall2()
	{
		new File("/").getFreeSpace();
	}

	private void latenceCall3()
	{
		latenceCall1();
	}

	private void latenceCall4()
	{
		latenceCall1();
		latenceCall2();
	}
}
