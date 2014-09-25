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

import com.octo.reactive.audit.lib.Latency;
import org.junit.Test;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

import static com.octo.reactive.audit.LoadParams.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LoadParamsTest
{
	private final AuditReactive config = AuditReactive.config;

	@Test
	public void setSystemParams() throws IOException
	{
		LoadParams.resetAllEnv();
		System.setProperty(KEY_LOG_LEVEL, Level.INFO.getName());
		System.setProperty(KEY_LOG_OUTPUT, "console");
		System.setProperty(KEY_LOG_FORMAT, "format");
		System.setProperty(KEY_THROW_EXCEPTIONS, "true");
		System.setProperty(KEY_THREAD_PATTERN, "abc");
		System.setProperty(KEY_BOOTSTRAP_DELAY, "10");
		new LoadParams(AuditReactive.config, DEFAULT_FILENAME).commit();
		assertEquals(Level.INFO, config.getLogLevel());
		assertTrue(config.logger.getHandlers()[0] instanceof ConsoleHandler);
		assertEquals("format", ((AuditLogFormat) config.logger.getHandlers()[0].getFormatter()).getFormat());
		assertEquals(true, config.isThrow());
		assertEquals("abc", config.getThreadPattern());
		assertEquals(10, config.getBootstrapDelay());
	}

	@Test
	public void loadNotFoundFile()
	{
		LoadParams.resetAllEnv();
		System.setProperty(KEY_LOG_LEVEL, "FINE");
		new LoadParams(config, "XXX").commit();
	}

	@Test
	public void loadNoFile()
	{
		new LoadParams(config, "").commit();
	}

	@Test(expected = IllegalArgumentException.class)
	public void badLogLevel()
	{
		LoadParams.resetAllEnv();
		System.setProperty(KEY_LOG_LEVEL, "XXX");
		new LoadParams(AuditReactive.config, DEFAULT_FILENAME).commit();
	}

	@Test
	public void mappingLogLevel()
	{
		LoadParams.resetAllEnv();
		System.setProperty(KEY_LOG_LEVEL, Latency.HIGH.name());
		new LoadParams(AuditReactive.config, DEFAULT_FILENAME).commit();
		assertEquals(Level.SEVERE, AuditReactive.config.logger.getLevel());

		LoadParams.resetAllEnv();
		System.setProperty(KEY_LOG_LEVEL, Latency.MEDIUM.name());
		new LoadParams(AuditReactive.config, DEFAULT_FILENAME).commit();
		assertEquals(Level.WARNING, AuditReactive.config.logger.getLevel());

		LoadParams.resetAllEnv();
		System.setProperty(KEY_LOG_LEVEL, Latency.LOW.name());
		new LoadParams(AuditReactive.config, DEFAULT_FILENAME).commit();
		assertEquals(Level.INFO, AuditReactive.config.logger.getLevel());
	}

}
