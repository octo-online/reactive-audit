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

class LoadParams
{
	public static final  String DEFAULT_THREAD_PATTERN   = "(^.*$)";
	public static final  String KEY_AUDIT_FILENAME       = "reactiveAudit";
	private static final String PREFIX                   = KEY_AUDIT_FILENAME + '_';
	public static final  String KEY_THROW_EXCEPTIONS     = PREFIX + "throwExceptions";
	public static final  String KEY_THREAD_PATTERN       = PREFIX + "threadPattern";
	public static final  String KEY_BOOTSTRAP_MODE       = PREFIX + "bootstrapMode";
	public static final  String KEY_BOOTSTRAP_CPUDELAY   = PREFIX + "bootstrapCPUDelay";
	public static final  String KEY_BOOTSTRAP_DELAY      = PREFIX + "bootstrapDelay";
	public static final String KEY_FILE_LATENCY          = PREFIX + "file";
	public static final String KEY_NETWORK_LATENCY       = PREFIX + "network";
	public static final String KEY_CPU_LATENCY           = PREFIX + "cpu";
	public static final  String KEY_LOG_OUTPUT           = PREFIX + "logOutput";
	public static final  String KEY_LOG_FORMAT           = PREFIX + "logFormat";
	private static final String KEY_LOG_SIZE             = PREFIX + "logSize";
	private static final String KEY_DEBUG                = PREFIX + "debug";
	public static final  String DEFAULT_FILENAME         = "reactiveAudit.properties";
	public static final  Level  DEFAULT_LOG_LEVEL        = Level.CONFIG;
	public static final  String DEFAULT_LOG_OUTPUT       = "console";
	public static final  String DEFAULT_LOG_FORMAT       = "%4$-7S: %5$s%6$s%n";
	public static final  String DEFAULT_LOG_SIZE         = "0"; // No limit
	private static final String DEFAULT_FILE_LATENCY     = Latency.MEDIUM.name();
	private static final String DEFAULT_NETWORK_LATENCY  = Latency.LOW.name();
	private static final String DEFAULT_CPU_LATENCY      = Latency.LOW.name();
	private static final String DEFAULT_BOOTSTRAP_MODE   = "delay";
	public static final  String DEFAULT_BOOTSTRAP_CPUDELAY = "2";
	private static final String DEFAULT_BOOTSTRAP_DELAY  = "0";
	private static final String DEFAULT_THROW_EXCEPTIONS = "false";
	private static final String DEFAULT_DEBUG            = "false";
	private static Properties                allEnv;
	private final  ReactiveAudit             config;
	private final  ReactiveAudit.Transaction tx;
	private        URL                       filename;

	public LoadParams(ReactiveAudit config, String propertiesFile)
	{
		this.config = config;
		config.logger.setLevel(Level.CONFIG);
		tx = config.begin();
		if (propertiesFile == null || propertiesFile.length() == 0)
		{
			return;
		}
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
				config.logger.config(propertiesFile + " malformed");
			}
			catch (IOException e1)
			{
				config.logger.config(propertiesFile + " malformed");
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
				config.logger.config(filename + " malformed");
			}
		}
	}

	/*package*/
	static void resetAllEnv()
	{
		allEnv = null;
	}

	private static Properties getAllEnv()
	{
        if (allEnv == null)
		{
			Properties tempAllEnv = new Properties();
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

	private static String getValue(String key, String def, Properties prop)
	{
		String val = prop.getProperty(key);
		if (val == null)
			val = def;
		return (val != null) ? val.trim() : null;
	}

	void commit()
	{
		Properties prop = new Properties();
		try
		{
			config.incSuppress();
			if (filename != null)
			{
				Reader reader = null;
				try
				{
					reader = new InputStreamReader(filename.openStream());
					prop.load(reader);
				}
				finally
				{
					if (reader != null)
					{
						reader.close();
					}
				}
			}
			prop.putAll(getAllEnv());
			applyProperties(new VariablesProperties(prop));
		}
		catch (IOException e)
		{
			config.logger.config(FileTools.homeFile(filename.toExternalForm()) + " not found. Use default values.");
		}
		finally
		{
			config.decSuppress();
		}
	}

	private void applyProperties(Properties prop)
	{
		Boolean debug = Boolean.parseBoolean(getValue(KEY_DEBUG, DEFAULT_DEBUG, prop));
		tx.debug(debug);
		tx.bootStrapMode(ReactiveAudit.BootStrapMode.valueOf(getValue(KEY_BOOTSTRAP_MODE, DEFAULT_BOOTSTRAP_MODE, prop).toUpperCase()));
		tx.bootStrapCPUDelay(Long.parseLong(getValue(KEY_BOOTSTRAP_CPUDELAY, DEFAULT_BOOTSTRAP_CPUDELAY, prop)));
		tx.bootStrapDelay(Long.parseLong(getValue(KEY_BOOTSTRAP_DELAY, DEFAULT_BOOTSTRAP_DELAY, prop)));
		tx.throwExceptions(Boolean.parseBoolean(getValue(KEY_THROW_EXCEPTIONS, DEFAULT_THROW_EXCEPTIONS, prop)));
		tx.latencyFile(getValue(KEY_FILE_LATENCY, DEFAULT_FILE_LATENCY, prop));
		tx.latencyNetwork(getValue(KEY_NETWORK_LATENCY, DEFAULT_NETWORK_LATENCY, prop));
		tx.latencyCPU(getValue(KEY_CPU_LATENCY, DEFAULT_CPU_LATENCY, prop));
		tx.logOutput(getValue(KEY_LOG_OUTPUT, DEFAULT_LOG_OUTPUT, prop),
					 getValue(KEY_LOG_FORMAT, DEFAULT_LOG_FORMAT, prop),
					 getValue(KEY_LOG_SIZE, DEFAULT_LOG_SIZE, prop));
		String threadPattern = getValue(KEY_THREAD_PATTERN, DEFAULT_THREAD_PATTERN, prop);
		if (threadPattern.length() == 0)  // Empty is not allowed
			threadPattern = "(^.*$)";
		tx.threadPattern(threadPattern);
		tx.commit();
	}

	private static Level convLevelLatency(String level)
	{
		if (Latency.HIGH.name().equals(level))
		{
			return Level.SEVERE;
		}
		if (Latency.MEDIUM.name().equals(level))
		{
			return Level.WARNING;
		}
		if (Latency.LOW.name().equals(level))
		{
			return Level.INFO;
		}
		return Level.parse(level);
	}
	public static String convLevelString(Level level)
	{
		if (level==Level.SEVERE)
		{
			return "high";
		}
		if (level==Level.WARNING)
		{
			return "medium";
		}
		if (level==Level.INFO)
		{
			return "low";
		}
		return "info";
	}
}
