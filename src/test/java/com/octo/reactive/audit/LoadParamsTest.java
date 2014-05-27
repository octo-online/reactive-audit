package com.octo.reactive.audit;

import org.junit.Test;

import java.io.IOException;

import static com.octo.reactive.audit.LoadParams.*;
import static com.octo.reactive.audit.Logger.Level.INFO;
import static org.junit.Assert.assertEquals;



/**
 * Created by pprados on 16/05/2014.
 */
public class LoadParamsTest
{
	ConfigAuditReactive config=ConfigAuditReactive.config;
	@Test
	public void setSystemParams() throws IOException
	{
		System.setProperty(KEY_LOG_LEVEL,INFO.name());
		System.setProperty(KEY_THROW_EXCEPTIONS,"true");
		System.setProperty(KEY_THREAD_PATTERN,"abc");
		System.setProperty(KEY_BOOTSTRAP_DELAY,"10");
		new LoadParams(ConfigAuditReactive.config,DEFAULT_FILENAME).commit();
		assertEquals(INFO, config.getLogLevel());
		assertEquals(true,config.isThrow());
		assertEquals("abc",config.getThreadPattern());
		assertEquals(10,config.getBootstrapDelay());
	}
	@Test
	public void loadNotFoundFile()
	{
		System.setProperty(KEY_LOG_LEVEL,"Debug");
		new LoadParams(config,"XXX").commit();
	}
	@Test
	public void loadNoFile()
	{
		new LoadParams(config,"").commit();
	}

	@Test(expected=IllegalArgumentException.class)
	public void badLogLevel()
	{
		System.setProperty(KEY_LOG_LEVEL,"XXX");
		new LoadParams(ConfigAuditReactive.config,DEFAULT_FILENAME).commit();
	}
}
