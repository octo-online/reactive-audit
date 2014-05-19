package com.octo.reactive.audit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

/**
 * Created by pprados on 16/05/2014.
 */
public class LoadParams
{
	public static final String DEFAULT_FILENAME="testAuditReactive.properties";
	private static final String PREFIX="auditReactive.";

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
				filename = new File(propertiesFile).toURI().toURL();
			}
			catch (MalformedURLException ee)
			{
				config.warn(propertiesFile+" malformed");
			}
		}
	}

	private void parseThrowException(String param)
	{
		tx.throwExceptions(Boolean.parseBoolean(param));
	}
	private void parseBootstrapDelay(String param)
	{
		tx.bootStrapDelay(Long.parseLong(param));
	}
	private void parseLog(String param)
	{
		switch (param.toLowerCase())
		{
			case "error" : tx.log(ConfigAuditReactive.Error); break;
			case "warn" : tx.log(ConfigAuditReactive.Warn); break;
			case "info" : tx.log(ConfigAuditReactive.Info); break;
			case "debug" : tx.log(ConfigAuditReactive.Debug); break;
			default: tx.log(ConfigAuditReactive.None); break;
		}
	}
	private void parseThreadPattern(String param)
	{
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
				applyProperties(prop, "");
			}

		}
		catch (IOException e)
		{
			config.info(filename + " not found");
		}
		// 3. Set from JVM -D
		applyProperties(System.getProperties(), PREFIX);
		tx.commit();
		config.debug("threadPattern  = " + config.getThreadPattern());
		config.debug("throwExceptions= "+config.isThrow());
		config.debug("bootstrapDelay = " + config.getBootstrapDelay());
	}
	private void applyEnv(String prefix)
	{
		String param;
		param=System.getenv(prefix+"throwExceptions");
		if (param!=null) parseThrowException(param);
		param=System.getenv(prefix+"threadPattern");
		if (param!=null)parseThreadPattern(param);
		param=System.getenv(prefix+"bootstrapDelay");
		if (param!=null) parseBootstrapDelay(param);
		param=System.getenv(prefix+"logLevel");
		if (param!=null) parseLog(param);
	}
	private void applyProperties(Properties prop,String prefix)
	{
		String param;
		param=prop.getProperty(prefix + "throwExceptions");
		if (param!=null) parseThrowException(param);
		param=prop.getProperty(prefix + "threadPattern");
		if (param!=null)parseThreadPattern(param);
		param=prop.getProperty(prefix + "bootstrapDelay");
		if (param!=null) parseBootstrapDelay(param);
		param=prop.getProperty(prefix + "logLevel");
		if (param!=null) parseLog(param);
	}
}
