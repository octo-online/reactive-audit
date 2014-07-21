package com.octo.reactive.audit;

import org.junit.Test;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

import static com.octo.reactive.audit.LoadParams.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by pprados on 16/05/2014.
 */
public class LoadParamsTest
{
	ConfigAuditReactive config = ConfigAuditReactive.config;

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
		new LoadParams(ConfigAuditReactive.config, DEFAULT_FILENAME).commit();
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
		new LoadParams(ConfigAuditReactive.config, DEFAULT_FILENAME).commit();
	}
}
