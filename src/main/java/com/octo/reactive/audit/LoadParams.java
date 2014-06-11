package com.octo.reactive.audit;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Created by pprados on 16/05/2014.
 */
public class LoadParams
{
	public static final String DEFAULT_THREAD_PATTERN   = "^(ForkJoinPool-.*)";
	public static final String DEFAULT_LOG_OUTPUT       = "%h/audit-reactive-%u.log";
	public static final String DEFAULT_LOG_FORMAT       = "%4$-7s: %5$s%6$s%n";
	public static final String DEFAULT_BOOTSTRAP_DELAY  = "0";
	public static final String DEFAULT_THROW_EXCEPTIONS = "false";

	public static final String KEY_AUDIT_FILENAME   = "auditReactive";
	public static final String PREFIX               = KEY_AUDIT_FILENAME + '.';
	public static final String KEY_THROW_EXCEPTIONS = PREFIX + "throwExceptions";
	public static final String KEY_THREAD_PATTERN   = PREFIX + "threadPattern";
	public static final String KEY_BOOTSTRAP_DELAY  = PREFIX + "bootstrapDelay";
	public static final String KEY_LOG_LEVEL        = PREFIX + "logLevel";
	public static final String KEY_LOG_OUTPUT       = PREFIX + "logOutput";
	public static final String KEY_LOG_FORMAT       = PREFIX + "logFormat";

	public static final String DEFAULT_FILENAME = "testAuditReactive.properties";

	private ConfigAuditReactive             config;
	private ConfigAuditReactive.Transaction tx;
	private URL                             filename;

	public LoadParams(ConfigAuditReactive config, String propertiesFile)
	{
		this.config = config;
		tx = config.begin();
		if (propertiesFile == null || propertiesFile.length() == 0)
			return;
		try
		{
			filename = new URL(propertiesFile);
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
				config.logger.warning(propertiesFile + " malformed");
			}
		}
	}

	public static String getValue(String key, String def, Properties prop)
	{
		String val = System.getenv(key);
		String newVal = System.getProperty(key);
		if (newVal != null) val = newVal;
		if (prop != null)
		{
			newVal = prop.getProperty(key);
			if (newVal != null)
				val = newVal;
		}
		if (val == null)
			val = def;
		return val.trim();
	}

	void commit()
	{
		Properties prop = new Properties();
		try
		{
			// Load from file (set with -D${DEFAULT_FILENAME}
			if (filename != null)
			{
				prop.load(filename.openStream());
			}
		}
		catch (IOException e)
		{
			config.logger.info(filename + " not found");
		}
		applyProperties(prop);
		config.logger.fine(KEY_THREAD_PATTERN + "  = " + config.getThreadPattern());
		config.logger.fine(KEY_THROW_EXCEPTIONS + " = " + config.isThrow());
		config.logger.fine(KEY_BOOTSTRAP_DELAY + " = " + config.getBootstrapDelay());
	}

	private void applyProperties(Properties prop)
	{
		tx.bootStrapDelay(Long.parseLong(getValue(KEY_BOOTSTRAP_DELAY, DEFAULT_BOOTSTRAP_DELAY, prop)));
		tx.throwExceptions(Boolean.parseBoolean(getValue(KEY_THROW_EXCEPTIONS, DEFAULT_THROW_EXCEPTIONS, prop)));
		tx.log(Level.parse(getValue(KEY_LOG_LEVEL, Level.WARNING.getName(), prop).toUpperCase()));
		tx.logOutput(getValue(KEY_LOG_OUTPUT, DEFAULT_LOG_OUTPUT, prop),
		             getValue(KEY_LOG_FORMAT, DEFAULT_LOG_FORMAT, prop));
		tx.threadPattern(getValue(KEY_THREAD_PATTERN, DEFAULT_THREAD_PATTERN, prop));
		tx.commit();
	}

}
