package com.octo.reactive.audit.java.util;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;

import java.io.*;
import java.util.Properties;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 19/05/2014.
 */
public class PropertiesTest
{
	private File getFileIn() throws IOException
	{
		push();
		File f = File.createTempFile("temp-file-name", ".tmp");
		f.delete();
		f.deleteOnExit();
		f.createNewFile();
		pop();
		return f;
	}

	@Test(expected = AuditReactiveException.class)
	public void load_FileInputStream() throws IOException
	{
		ConfigAuditReactive.off.commit();
		InputStream in = new FileInputStream(getFileIn());
		ConfigAuditReactive.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}

	@Test(expected = AuditReactiveException.class)
	public void load_FileReader() throws IOException
	{
		ConfigAuditReactive.off.commit();
		Reader in = new FileReader(getFileIn());
		ConfigAuditReactive.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}

	@Test
	public void load_InputStream() throws IOException
	{
		ConfigAuditReactive.off.commit();
		InputStream in = new ByteArrayInputStream(new byte[1]);
		ConfigAuditReactive.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}

	@Test
	public void load_Reader() throws IOException
	{
		ConfigAuditReactive.off.commit();
		Reader in = new StringReader("");
		ConfigAuditReactive.strict.commit();
		Properties prop = new Properties();
		prop.load(in);
	}
}
