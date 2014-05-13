package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import com.octo.reactive.audit.ConfigAuditReactive;
import org.junit.Test;
import static com.octo.reactive.audit.TestTools.*;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class BufferedReaderTest extends InputStreamReaderTest
{
	@Override
	protected Reader newReader() throws IOException
	{
		push();
		Reader reader=super.newReader();
		pop();
		return new BufferedReader(reader);
	}
	@Test(expected=AuditReactiveException.class)
	public void derived() throws IOException
	{
		class Derived extends BufferedReader
		{
			Derived() throws IOException
			{
				super(new InputStreamReader(new FileInputStream(getFileIn())));
			}
		};
		new Derived();
	}
}
