package com.octo.reactive.audit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;

/**
 * Created by pprados on 16/05/2014.
 */
class LoadParams
{
	public static final String KEY_AUDIT_FILENAME="auditReactive";
	private static final String PREFIX=KEY_AUDIT_FILENAME+'.';
	public static final String KEY_THROW_EXCEPTIONS=PREFIX+"throwExceptions";
	public static final String KEY_THREAD_PATTERN=PREFIX+"threadPattern";
	public static final String KEY_BOOTSTRAP_DELAY=PREFIX+"bootstrapDelay";
	public static final String KEY_LOG_LEVEL=PREFIX+"logLevel";

	public static final String DEFAULT_FILENAME="testAuditReactive.properties";

	private ConfigAuditReactive             config;
	private ConfigAuditReactive.Transaction tx;
	private URL filename;

	public LoadParams(ConfigAuditReactive config,String propertiesFile)
	{
		propertiesFile=propertiesFile.trim();
		this.config = config;
		tx = config.begin();
		if (propertiesFile==null || propertiesFile.length()==0)
			return;
		try
		{
			filename=new URL(propertiesFile);
		}
		catch (MalformedURLException e)
		{
			try
			{
				URI uri = new File(propertiesFile).toURI();
				filename = uri.toURL();
			}
			catch (MalformedURLException ee)
			{
				config.logger.warn(propertiesFile+" malformed");
			}
		}
	}

	private void parseThrowException(String param)
	{
		param=param.trim();
		if (param.length()==0) return;
		tx.throwExceptions(Boolean.parseBoolean(param));
	}
	private void parseBootstrapDelay(String param)
	{
		param=param.trim();
		if (param.length()==0) return;
		tx.bootStrapDelay(Long.parseLong(param));
	}
	private void parseLog(String param)
	{
		param=param.trim();
		if (param.length()==0) return;
		tx.log(Logger.Level.valueOf(param.toUpperCase()));
	}
	private void parseThreadPattern(String param)
	{
		param=param.trim();
		if (param.length()==0) return;
		tx.threadPattern(param);
	}
	//throwExceptions=false
	void commit()
	{
		try
		{
			// 1. Set from system env.
			applyEnv(PREFIX);

			// 2. Set from file (set with -D${DEFAULT_FILENAME}
			Properties prop=new Properties();
			if (filename!=null)
			{
				prop.load(filename.openStream());
				applyProperties(prop);
			}

		}
		catch (IOException e)
		{
			config.logger.info(filename + " not found");
		}
		// 3. Set from JVM -D
		applyProperties(System.getProperties());
		tx.commit();
		config.logger.debug(KEY_THREAD_PATTERN+"  = " + config.getThreadPattern());
		config.logger.debug(KEY_THROW_EXCEPTIONS+" = "+config.isThrow());
		config.logger.debug(KEY_BOOTSTRAP_DELAY+" = " + config.getBootstrapDelay());
	}
	private void applyEnv(String prefix)
	{
		String param;
		param=System.getenv(KEY_BOOTSTRAP_DELAY);
		if (param!=null) parseBootstrapDelay(param);
		param=System.getenv(KEY_THROW_EXCEPTIONS);
		if (param!=null) parseThrowException(param);
		param=System.getenv(KEY_LOG_LEVEL);
		if (param!=null) parseLog(param);
		param=System.getenv(KEY_THREAD_PATTERN);
		if (param!=null)parseThreadPattern(param);
	}
	private void applyProperties(Properties prop)
	{
		if (prop==null) return;
		String param;
		param=prop.getProperty(KEY_BOOTSTRAP_DELAY);
		if (param!=null) parseBootstrapDelay(param);
		param=prop.getProperty(KEY_THROW_EXCEPTIONS);
		if (param!=null) parseThrowException(param);
		param=prop.getProperty(KEY_LOG_LEVEL);
		if (param!=null) parseLog(param);
		param=prop.getProperty(KEY_THREAD_PATTERN);
		if (param!=null)parseThreadPattern(param);
	}
}
