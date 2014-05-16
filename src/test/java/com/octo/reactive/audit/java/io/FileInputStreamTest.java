package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;

import java.io.*;
import static com.octo.reactive.audit.TestTools.*;

/**
 * Created by pprados on 06/05/14.
 */
public class FileInputStreamTest extends AuditedInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		return new FileInputStream(getFileIn());
	}

	private File getFileIn() throws IOException
    {
        push();
        File f=File.createTempFile("temp-file-name", ".tmp");
        f.delete();
        f.deleteOnExit();
        f.createNewFile();
	    pop();
        return f;
    }
	@Override
	@Test(expected=AuditReactiveException.class)
	public void New() throws IOException
	{
		super.New();
	}
	@Test(expected=AuditReactiveException.class)
	public void derived() throws IOException
	{
		class Derived extends FileInputStream
		{
			Derived() throws IOException
			{
				super(getFileIn());
			}
		};
		new Derived();
	}


}
