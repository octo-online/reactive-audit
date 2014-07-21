package com.octo.reactive.audit;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

/**
 * Created by pprados on 16/05/2014.
 */
public class LoadParams
{
	public static final String DEFAULT_FILE_LATENCY     = "MEDIUM";
	public static final String DEFAULT_NETWORK_LATENCY  = "LOW";
	public static final String DEFAULT_CPU_LATENCY      = "LOW";
	public static final String DEFAULT_THREAD_PATTERN   = "^(ForkJoinPool-.*)"; // FIXME "(?!^main$)(^.*$)"
	public static final String DEFAULT_LOG_LEVEL        = Level.WARNING.getName();
	public static final String DEFAULT_LOG_OUTPUT       = "%h/audit-reactive-%u.log";
	public static final String DEFAULT_LOG_FORMAT       = "%4$-7s: %5$s%6$s%n";
	public static final String DEFAULT_LOG_SIZE         = "0"; // No limit
	public static final String DEFAULT_BOOTSTRAP_DELAY  = "0";
	public static final String DEFAULT_THROW_EXCEPTIONS = "false";
	public static final String DEFAULT_DEBUG            = "false";

	public static final String KEY_AUDIT_FILENAME   = "auditReactive";
	public static final String PREFIX               = KEY_AUDIT_FILENAME + '.';
	public static final String KEY_THROW_EXCEPTIONS = PREFIX + "throwExceptions";
	public static final String KEY_THREAD_PATTERN   = PREFIX + "threadPattern";
	public static final String KEY_BOOTSTRAP_DELAY  = PREFIX + "bootstrapDelay";
	public static final String KEY_FILE_LATENCY     = PREFIX + "file";
	public static final String KEY_NETWORK_LATENCY  = PREFIX + "network";
	public static final String KEY_CPU_LATENCY      = PREFIX + "cpu";
	public static final String KEY_LOG_LEVEL        = PREFIX + "logLevel";
	public static final String KEY_LOG_OUTPUT       = PREFIX + "logOutput";
	public static final String KEY_LOG_FORMAT       = PREFIX + "logFormat";
	public static final String KEY_LOG_SIZE         = PREFIX + "logSize";
	public static final String KEY_DEBUG            = PREFIX + "debug";

	public static final String DEFAULT_FILENAME = "auditReactive.properties";
	private static Properties                      allEnv;
	private        ConfigAuditReactive             config;
	private        ConfigAuditReactive.Transaction tx;
	private        URL                             filename;

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
				URI uri = new File(propertiesFile).getCanonicalFile().toURI();
				filename = uri.toURL();
			}
			catch (MalformedURLException ee)
			{
				config.logger.warning(propertiesFile + " malformed");
			}
			catch (IOException e1)
			{
				config.logger.warning(propertiesFile + " malformed");
			}
		}
		// @See bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=438989
		if ((filename != null) && !filename.getPath().endsWith(".properties"))
		{
			try
			{
				filename = new URL(filename.toExternalForm() + ".properties");
			}
			catch (MalformedURLException e)
			{
				config.logger.warning(filename + " malformed");
			}
		}
	}

	/*package*/
	static void resetAllEnv()
	{
		allEnv = null;
	}

	protected static Properties getAllEnv()
	{
		if (allEnv == null)
		{
			Properties tempAllEnv = new Properties(); // FIXME
			// First: Java properties
			for (Map.Entry<Object, Object> entry : System.getProperties().entrySet())
			{
				tempAllEnv.put(entry.getKey(), entry.getValue());
			}
			// Second: overflow with ENV
			Map<String, String> map = System.getenv();
			for (Map.Entry<String, String> entry : map.entrySet())
			{
				tempAllEnv.put(entry.getKey(), entry.getValue());
			}
			allEnv = tempAllEnv;
		}
		return allEnv;
	}

	public static String getValue(String key, String def, Properties prop)
	{
		String val = getAllEnv().getProperty(key);
		String newVal = null;
		if (prop != null)
		{
			newVal = prop.getProperty(key);
			if (newVal != null)
				val = newVal;
		}
		if (newVal != null) val = newVal;
		if (val == null)
			val = def;
		return (val != null) ? val.trim() : null;
	}

	void commit()
	{
		Properties prop = new VariablesProperties(getAllEnv());
		try
		{
			if (filename != null)
			{
				try (Reader reader = new InputStreamReader(filename.openStream()))
				{
					prop.load(reader);
				}
			}
		}
		catch (IOException e)
		{
			config.logger.warning(filename + " not found");
		}
		applyProperties(prop);
		config.logger.config(KEY_THREAD_PATTERN + "  = " + config.getThreadPattern());
		config.logger.config(KEY_THROW_EXCEPTIONS + " = " + config.isThrow());
		config.logger.config(KEY_BOOTSTRAP_DELAY + " = " + config.getBootstrapDelay());
		config.logger.config(KEY_FILE_LATENCY + " = " + config.getFileLatency());
		config.logger.config(KEY_NETWORK_LATENCY + " = " + config.getNetworkLatency());
		config.logger.config(KEY_CPU_LATENCY + " = " + config.getCPULatency());
	}

	private void applyProperties(Properties prop)
	{
		Boolean debug = Boolean.parseBoolean(getValue(KEY_DEBUG, DEFAULT_DEBUG, prop));
		if (debug) tx.debug(debug);
		tx.bootStrapDelay(Long.parseLong(getValue(KEY_BOOTSTRAP_DELAY, DEFAULT_BOOTSTRAP_DELAY, prop)));
		tx.throwExceptions(Boolean.parseBoolean(getValue(KEY_THROW_EXCEPTIONS, DEFAULT_THROW_EXCEPTIONS, prop)));
		tx.latencyFile(getValue(KEY_FILE_LATENCY, DEFAULT_FILE_LATENCY, prop));
		tx.latencyNetwork(getValue(KEY_NETWORK_LATENCY, DEFAULT_NETWORK_LATENCY, prop));
		tx.latencyCPU(getValue(KEY_CPU_LATENCY, DEFAULT_CPU_LATENCY, prop));
		tx.log(Level.parse(getValue(KEY_LOG_LEVEL, DEFAULT_LOG_LEVEL, prop).toUpperCase()));
		tx.logOutput(getValue(KEY_LOG_OUTPUT, DEFAULT_LOG_OUTPUT, prop),
		             getValue(KEY_LOG_FORMAT, DEFAULT_LOG_FORMAT, prop),
		             getValue(KEY_LOG_SIZE, DEFAULT_LOG_SIZE, prop));
		String threadPattern = getValue(KEY_THREAD_PATTERN, DEFAULT_THREAD_PATTERN, prop);
		if (threadPattern.length() == 0)  // Empty is not allowed
			threadPattern = "(^.*$)";
		tx.threadPattern(threadPattern);
		tx.commit();
	}

}
