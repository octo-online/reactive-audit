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

import org.junit.Test;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static com.octo.reactive.audit.LoadParams.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LoadParamsTest
{
	private final ReactiveAudit config = ReactiveAudit.config;

	@Test
	public void setSystemParams()
			throws IOException
	{
		try
		{
			LoadParams.resetAllEnv();
			System.setProperty(KEY_LOG_OUTPUT, "console");
			System.setProperty(KEY_LOG_FORMAT, "format");
			System.setProperty(KEY_THROW_EXCEPTIONS, "true");
			System.setProperty(KEY_THREAD_PATTERN, "abc");
			System.setProperty(KEY_BOOTSTRAP_DELAY, "10");
			System.setProperty(KEY_BOOTSTRAP_MODE, "annotation");
			new LoadParams(ReactiveAudit.config, null).commit();
			assertTrue(config.logger.getHandlers()[0] instanceof ConsoleHandler);
			assertEquals("format", ((AuditLogFormat) config.logger.getHandlers()[0].getFormatter()).getFormat());
			assertEquals(true, config.isThrow());
			assertEquals("abc", config.getThreadPattern());
			assertEquals(ReactiveAudit.BootStrapMode.ANNOTATION,config.getBootStrapMode());
			assertEquals(10, config.getBootstrapDelay());
		}
		finally
		{
			ReactiveAudit.config.reset();
			System.clearProperty(KEY_LOG_OUTPUT);
			System.clearProperty(KEY_LOG_FORMAT);
			System.clearProperty(KEY_THROW_EXCEPTIONS);
			System.clearProperty(KEY_THREAD_PATTERN);
			System.clearProperty(KEY_BOOTSTRAP_MODE);
			System.clearProperty(KEY_BOOTSTRAP_DELAY);
			LoadParams.resetAllEnv();
		}
	}


	@Test
	public void loadNotFoundFile()
	{
		config.reset();
		LoadParams.resetAllEnv();
		for (Handler h : config.logger.getHandlers())
		{
			config.logger.removeHandler(h);
		}
		final Handler handler = new Handler()
		{
			@Override
			public void publish(LogRecord record)
			{
				assert (record.getMessage().indexOf("not found") != 0);
			}

			@Override
			public void flush()
			{

			}

			@Override
			public void close()
					throws SecurityException
			{

			}
		};
		config.logger.addHandler(handler);
		try
		{
			new LoadParams(config, "XXX").commit();
		}
		finally
		{
			config.logger.removeHandler(handler);
		}
	}

	@Test
	public void loadNoFile()
	{
		new LoadParams(config, "").commit();
	}

	@Test
	public void variableProperties()
	{
		LoadParams.resetAllEnv();
		String url = getClass().getResource("/testEnv.properties").toExternalForm();
		ReactiveAudit.config.incSuppress();
		try
		{
			new LoadParams(config, url).commit();
			assertEquals(config.getThreadPattern(), System.getProperty("os.name"));
		}
		finally
		{
			ReactiveAudit.config.decSuppress();
		}
	}


}
