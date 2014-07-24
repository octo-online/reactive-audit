package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 06/05/14.
 */
public class FileReaderTest extends InputStreamReaderTest
{
	//@Override
	protected Reader newReader() throws IOException
	{
		push();
		File fileIn = IOTestTools.getTempFile();
		pop();
		Reader in = new FileReader(fileIn);
		return in;
	}

	@Test(expected = FileAuditReactiveException.class)
	@Override
	public void New() throws IOException
	{
		AuditReactive.strict.commit();
		try (Reader in = new FileReader(IOTestTools.getTempFile()))
		{
			AuditReactive.off.commit();
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void New_String() throws IOException
	{
		AuditReactive.strict.commit();
		try (Reader in = new FileReader(IOTestTools.getTempFile().getName()))
		{
			AuditReactive.off.commit();
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void derived() throws IOException
	{
		class Derived extends FileReader
		{
			Derived() throws IOException
			{
				super(IOTestTools.getTempFile().getName());
			}
		}
		;
		new Derived();
	}
}
