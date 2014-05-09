package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class FileInputStreamTest extends InputStreamTest
{
	protected InputStream newInputStream() throws IOException
	{
		return new FileInputStream(getFile());
	}

	private File getFile() throws IOException
    {
        ConfigAuditReactive.Transaction save=ConfigAuditReactive.config.current();
        ConfigAuditReactive.off.commit();
        File f=File.createTempFile("temp-file-name", ".tmp");
        f.delete();
        f.deleteOnExit();
        f.createNewFile();
        save.commit();
        return f;
    }
	@Test(expected=AuditReactiveException.class)
	public void New() throws IOException
	{
		super.New();
	}

	@Test(expected=AuditReactiveException.class)
	public void available() throws IOException
	{
		super.available();
	}
	@Test(expected=AuditReactiveException.class)
	public void close() throws IOException
	{
		super.close();
	}
	@Test(expected=AuditReactiveException.class)
	public void read() throws IOException
	{
		super.read();
	}
	@Test(expected=AuditReactiveException.class)
	public void read_B() throws IOException
	{
		super.read_B();
	}
	@Test(expected=AuditReactiveException.class)
	public void read_Bii() throws IOException
	{
		super.read_Bii();
	}
	@Test(expected=AuditReactiveException.class)
	public void skip() throws IOException
	{
		super.skip();
	}

}
