package com.octo.reactive.audit.java.util;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

/**
 * Created by pprados on 19/05/2014.
 */
public class PropertiesTest
{
	@Test(expected = AuditReactiveException.class)
	public void load_FileInputStream() throws IOException
	{
		AuditReactive.off.commit();
		InputStream in = new FileInputStream(IOTestTools.getTempFile());
		AuditReactive.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}

	@Test(expected = AuditReactiveException.class)
	public void load_FileReader() throws IOException
	{
		AuditReactive.off.commit();
		Reader in = new FileReader(IOTestTools.getTempFile());
		AuditReactive.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}

	@Test
	public void load_InputStream() throws IOException
	{
		AuditReactive.off.commit();
		InputStream in = new ByteArrayInputStream(new byte[1]);
		AuditReactive.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}

	@Test
	public void load_Reader() throws IOException
	{
		AuditReactive.off.commit();
		Reader in = new StringReader("");
		AuditReactive.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}
}
