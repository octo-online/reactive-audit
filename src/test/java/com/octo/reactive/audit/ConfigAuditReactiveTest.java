package com.octo.reactive.audit;

import com.octo.reactive.audit.annotation.SuppressAuditReactive;
import org.junit.Test;

import java.io.File;

import static com.octo.reactive.audit.Logger.Level;
import static com.octo.reactive.audit.Logger.Level.*;
import static org.junit.Assert.*;


/**
 * Created by pprados on 07/05/2014.
 */
public class ConfigAuditReactiveTest
{
	// Because the Aspectj must use the config singleton,
	// it's not possible to inject a specific config instance
	private ConfigAuditReactive config=ConfigAuditReactive.config;

	@Test
	public void testCurrentThread_test()
	{
		assertTrue(config.isThreadNameMatch(Thread.currentThread().getName()));
	}
	@Test
	public void testCurrentThread_nothing()
	{
		config.begin().threadPattern("(?!)").commit();
		assertFalse(config.isThreadNameMatch(Thread.currentThread().getName()));
	}
	@Test
	public void setAllParams()
	{
		config.begin()
				.log(INFO)
				.throwExceptions(true)
				.threadPattern("")
				.bootStrapDelay(10)
				.commit();
		assertEquals(INFO,config.getLogLevel());
		assertEquals(true,config.isThrow());
		assertEquals("",config.getThreadPattern());
		assertEquals(10,config.getBootstrapDelay());
		config.begin()
				.log(WARN)
				.throwExceptions(false)
				.threadPattern("abc")
				.bootStrapDelay(0)
				.commit();
		assertEquals(WARN,config.getLogLevel());
		assertEquals(false,config.isThrow());
		assertEquals("abc",config.getThreadPattern());
		assertEquals(0,config.getBootstrapDelay());
	}
	@Test(expected = IllegalArgumentException.class)
	public void lockTransaction()
	{
		config.begin()
				.seal()
				.log(WARN);
	}

	@Test
	@SuppressAuditReactive // For accept join
	public void logIfNewThread() throws InterruptedException
	{
		config.begin()
				.threadPattern(".*")
				.log(WARN)
				.throwExceptions(false)
				.commit();
		int[] log=new int[1];
		config.logger.delegate = new Logger.DelegateLogger()
		{
			@Override
			public void log(Level level, CharSequence msg)
			{
				if (level!= DEBUG)
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
		config.reset();
		config.begin()
				.threadPattern(".*")
				.log(WARN)
				.throwExceptions(false)
				.commit();
		int[] log = new int[1];
		config.logger.delegate = new Logger.DelegateLogger()
		{
			@Override
			public void log(Level level, CharSequence msg)
			{
				if (level!= DEBUG)
					++log[0];
			}
		};

		for (int i = 0; i < 5; ++i)
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
