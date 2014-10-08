/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.TolerateLatency;
import com.octo.reactive.audit.lib.FileReactiveAuditException;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import org.junit.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import static com.octo.reactive.audit.ReactiveAudit.auditPackageName;
import static com.octo.reactive.audit.lib.Latency.*;
import static org.junit.Assert.*;


@SuppressWarnings({"MethodOnlyUsedFromInnerClass", "ResultOfMethodCallIgnored"})
public class ReactiveAuditTest
{
	// Because the Aspectj must use the config singleton,
	// it's not possible to inject a specific config instance
	private final ReactiveAudit config = ReactiveAudit.config;

	private final int[]   log     = new int[1];
	private final Handler handler = new Handler()
	{
		@Override
		public void publish(LogRecord record)
		{
			if (record.getLevel() != Level.FINE)
				++log[0];
		}

		@Override
		public void flush()
		{
		}

		@Override
		public void close()
		{
		}
	};


	@Test
	public void currentThread_test()
	{
		TestTools.strict.commit();
		assertTrue(config.isThreadNameMatch(Thread.currentThread().getName()));
	}

	@Test
	public void currentThread_nothing()
	{
		config.begin().threadPattern("(?!)").commit();
		assertFalse(config.isThreadNameMatch(Thread.currentThread().getName()));
	}

	@Test
	public void setAllParams()
	{
		config.reset();
		config.begin()
				.throwExceptions(true)
				.threadPattern("")
				.latencyFile("high")
				.latencyNetwork("medium")
				.latencyCPU("low")
				.bootStrapDelay(10)
				.commit();
		assertEquals(true, config.isThrow());
		assertEquals("", config.getThreadPattern());
		assertEquals(Latency.HIGH, config.getFileLatency());
		assertEquals(Latency.MEDIUM, config.getNetworkLatency());
		assertEquals(LOW, config.getCPULatency());
		assertEquals(10, config.getBootstrapDelay());
		config.begin()
				.throwExceptions(false)
				.threadPattern("abc")
				.latencyFile("")
				.latencyNetwork("")
				.latencyCPU("")
				.bootStrapDelay(0)
				.commit();
		assertEquals(false, config.isThrow());
		assertEquals("abc", config.getThreadPattern());
		assertNull(config.getFileLatency());
		assertNull(config.getNetworkLatency());
		assertNull(config.getCPULatency());
		assertEquals(0, config.getBootstrapDelay());
	}

	@Test(expected = IllegalArgumentException.class)
	public void lockTransaction()
	{
		config.reset();
		config.begin()
				.seal()
				.threadPattern("abc");
	}

	@Test
	@TolerateLatency // For accept join
	public void logIfNewThread()
			throws InterruptedException
	{
		config.reset();
		config.begin()
				.threadPattern(".*")
				.latencyFile("LOW")
				.throwExceptions(false)
				.commit();

		try
		{
			addHandler();
			Thread t;
			log[0] = 0;
			Runnable rctx1 = new Runnable()
			{
				@Override
				public void run()
				{
					latencyCall1();
				}
			};
			// First turn, invoke log.
			t = new Thread(rctx1);
			t.setDaemon(true);
			t.start();
			t.join();
			assertEquals(1, log[0]);

			// Second turn, invoke log.
			log[0] = 0;
			Runnable rctx2 = new Runnable()
			{
				@Override
				public void run()
				{
					latencyCall2();
				}
			};
			t = new Thread(rctx2);
			t.setDaemon(true);
			t.start();
			t.join();
			assertEquals(1, log[0]);

			// Third turn, same context, no invoke log.
			log[0] = 0;
			t = new Thread(rctx1);
			t.setDaemon(true);
			t.start();
			t.join();
			assertEquals(0, log[0]);

			log[0] = 0;
			Runnable rctx3 = new Runnable()
			{
				@Override
				public void run()
				{
					latencyCall3();
				}
			};
			t = new Thread(rctx3);
			t.setDaemon(true);
			t.start();
			t.join();
			assertEquals(1, log[0]);

			log[0] = 0;
			Runnable rctx4 = new Runnable()
			{
				@Override
				public void run()
				{
					latencyCall4();
				}
			};
			t = new Thread(rctx4);
			t.setDaemon(true);
			t.start();
			t.join();
			assertEquals(2, log[0]);

			log[0] = 0;
			t = new Thread(rctx3);
			t.setDaemon(true);
			t.start();
			t.join();
			assertEquals(0, log[0]);

			log[0] = 0;
			t = new Thread(rctx4);
			t.setDaemon(true);
			t.start();
			t.join();
			assertEquals(0, log[0]);

		}
		finally
		{
			removeHandler();
		}
	}

	@Test
	public void logIfNewLoop()
	{
		config.reset();
		config.begin()
				.threadPattern(".*")
				.logOutput(LoadParams.DEFAULT_LOG_OUTPUT,LoadParams.DEFAULT_LOG_FORMAT,LoadParams.DEFAULT_LOG_SIZE)
				.latencyFile("LOW")
				.throwExceptions(false)
				.commit();

		try
		{
			addHandler();
			for (int i = 0; i < 5; ++i)
			{
				log[0] = 0;
				latencyCall1();
				if (i == 0) assertEquals(1, log[0]);
				else assertEquals(0, log[0]);

			}
		}
		finally
		{
			removeHandler();
		}
	}

	@Test
	public void logIfLevel()
	{
		config.reset();
		config.begin()
				.latencyFile("")
				.commit();
		try
		{
			addHandler();
			log[0] = 0;
			config.logIfNew(LOW, new FileReactiveAuditException(LOW, ""));
			assertEquals(0, log[0]);
			log[0] = 0;
			config.logIfNew(MEDIUM, new FileReactiveAuditException(MEDIUM, ""));
			assertEquals(0, log[0]);
			log[0] = 0;
			config.logIfNew(HIGH, new FileReactiveAuditException(HIGH, ""));
			assertEquals(0, log[0]);
		}
		finally
		{
			removeHandler();
		}

		config.begin()
				.latencyFile("LOW")
				.commit();
		try
		{
			addHandler();
			log[0] = 0;
			config.logIfNew(LOW, new FileReactiveAuditException(LOW, ""));
			assertEquals(1, log[0]);
			log[0] = 0;
			config.logIfNew(MEDIUM, new FileReactiveAuditException(MEDIUM, ""));
			assertEquals(1, log[0]);
			log[0] = 0;
			config.logIfNew(HIGH, new FileReactiveAuditException(HIGH, ""));
			assertEquals(1, log[0]);
		}
		finally
		{
			removeHandler();
		}

		config.begin()
				.latencyFile("MEDIUM")
				.commit();
		try
		{
			addHandler();
			log[0] = 0;
			config.logIfNew(LOW, new FileReactiveAuditException(LOW, ""));
			assertEquals(0, log[0]);
			log[0] = 0;
			config.logIfNew(MEDIUM, new FileReactiveAuditException(MEDIUM, ""));
			assertEquals(1, log[0]);
			log[0] = 0;
			config.logIfNew(HIGH, new FileReactiveAuditException(HIGH, ""));
			assertEquals(1, log[0]);
		}
		finally
		{
			removeHandler();
		}

		config.begin()
				.latencyFile("HIGH")
				.commit();
		try
		{
			addHandler();
			log[0] = 0;
			config.logIfNew(LOW, new FileReactiveAuditException(LOW, ""));
			assertEquals(0, log[0]);
			log[0] = 0;
			config.logIfNew(MEDIUM, new FileReactiveAuditException(MEDIUM, ""));
			assertEquals(0, log[0]);
			log[0] = 0;
			config.logIfNew(HIGH, new FileReactiveAuditException(HIGH, ""));
			assertEquals(1, log[0]);
		}
		finally
		{
			removeHandler();
		}
	}

	private void removeHandler()
	{
		config.logger.removeHandler(handler);
	}

	@SuppressWarnings("ConstantConditions")
	private void addHandler()
	{
		//noinspection ConstantIfStatement
		if (true) // Remove log on console
		{
			for (Handler h : config.logger.getHandlers())
			{
				config.logger.removeHandler(h);
			}
		}
		config.logger.addHandler(handler);
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

	@SuppressWarnings("PointlessBooleanExpression")
	@Test
	public void testPurgeStackTrace()
			throws NoSuchFieldException, IllegalAccessException
	{
		// If debug mode, test nothing
		Field field = ReactiveAuditException.class.getDeclaredField("debug");
		field.setAccessible(true);
		if (((Boolean) field.get(null)) == true) return;
        TestTools.strict.commit();

		try
		{
			latencyCall1();
			fail();
		}
		catch (ReactiveAuditException e)
		{
			StackTraceElement[] stack = e.getStackTrace();
			for (StackTraceElement traceElement : stack)
			{
				assertFalse((traceElement.getClassName().startsWith(auditPackageName)
									 && !traceElement.getClassName().endsWith("Test"))); // For inner unit test
			}
		}
	}
}
