package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;
import static com.octo.reactive.audit.TestTools.*;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class FileReaderTest extends InputStreamReaderTest
{
	//@Override
	protected Reader newReader() throws IOException
	{
		push();
		File fileIn = getFileIn();
		pop();
		Reader in = new FileReader(fileIn);
		return in;
	}
	@Test(expected=AuditReactiveException.class)
	public void New_File() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		try (Reader in= new FileReader(getFileIn()))
		{
			ConfigAuditReactive.off.commit();
		}
	}
	@Test(expected=AuditReactiveException.class)
	public void New_String() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		try (Reader in= new FileReader(getFileIn().getName()))
		{
			ConfigAuditReactive.off.commit();
		}
	}
	@Test(expected=AuditReactiveException.class)
	public void read() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (Reader in= newReader())
		{
			ConfigAuditReactive.strict.commit();
			in.read();
		}
	}
	@Test
	public void derived() throws IOException
	{
		class Derived extends FileReader
		{
			Derived() throws IOException
			{
				super(getFileIn().getName());
			}
		};
		new Derived();
	}
}
