package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by pprados on 06/05/14.
 */
public class FileOutputStreamTest extends OutputStreamTest
{
	protected OutputStream newOutputStream() throws IOException
	{
		return new FileOutputStream(getFile());
	}

	private File getFile() throws IOException
    {
        ConfigAuditReactive.Transaction save=ConfigAuditReactive.config.current();
        ConfigAuditReactive.off.commit();
        File f=File.createTempFile("temp-file-name", ".tmp");
        f.delete();
        f.deleteOnExit();
        save.commit();
        return f;
    }
	@Test(expected=AuditReactiveException.class)
	public void New() throws IOException
	{
		super.New();
	}
	@Test(expected=AuditReactiveException.class)
	public void flush() throws IOException
	{
		super.flush();
	}
	@Test(expected=AuditReactiveException.class)
	public void close() throws IOException
	{
		super.close();
	}
	@Test(expected=AuditReactiveException.class)
	public void write_b() throws IOException
	{
		super.write_b();
	}
	@Test(expected=AuditReactiveException.class)
	public void write_B() throws IOException
	{
		super.write_B();
	}
	@Test(expected=AuditReactiveException.class)
	public void write_Bii() throws IOException
	{
		super.write_Bii();
	}
}
