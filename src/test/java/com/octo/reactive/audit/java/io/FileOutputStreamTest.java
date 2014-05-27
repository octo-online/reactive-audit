package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.annotation.FileAuditReactiveException;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 06/05/14.
 */
public class FileOutputStreamTest extends AuditOutputStreamTest
{
	@Override
	protected OutputStream newOutputStream() throws IOException
	{
		return new FileOutputStream(getFileOut());
	}

	private File getFileOut() throws IOException
    {
        push();
        File f=File.createTempFile("temp-file-name", ".tmp");
        f.delete();
        f.deleteOnExit();
	    pop();
        return f;
    }
	@Override
	@Test(expected=FileAuditReactiveException.class)
	public void New() throws IOException
	{
		super.New();
	}
	@Test(expected=FileAuditReactiveException.class)
	public void derived() throws IOException
	{
		class Derived extends FileOutputStream
		{
			Derived() throws IOException
			{
				super(getFileOut());
			}
		};
		new Derived();
	}
}
