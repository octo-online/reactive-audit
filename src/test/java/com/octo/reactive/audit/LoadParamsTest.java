package com.octo.reactive.audit;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by pprados on 16/05/2014.
 */
public class LoadParamsTest
{
	@Test
	public void setSystemParams() throws IOException
	{
		System.setProperty("auditReactive.logLevel","info");
		System.setProperty("auditReactive.throwExceptions","true");
		System.setProperty("auditReactive.threadPattern","abc");
		System.setProperty("auditReactive.bootstrapDelay","10");
		new LoadParams(ConfigAuditReactive.config,LoadParams.DEFAULT_FILENAME).commit();
		assertEquals(Logger.Info,ConfigAuditReactive.config.getLogLevel());
		assertEquals(true,ConfigAuditReactive.config.isThrow());
		assertEquals("abc",ConfigAuditReactive.config.getThreadPattern());
		assertEquals(10,ConfigAuditReactive.config.getBootstrapDelay());
	}
	@Test
	public void loadNotFoundFile()
	{
		new LoadParams(ConfigAuditReactive.config,"XXX").commit();
	}
	@Test
	public void loadNoFile()
	{
		new LoadParams(ConfigAuditReactive.config,"").commit();
	}
}
