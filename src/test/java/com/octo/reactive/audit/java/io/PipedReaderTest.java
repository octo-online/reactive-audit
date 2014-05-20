package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
// FIXME
public class PipedReaderTest //extends ReaderTest
{
	//@Override
	protected Reader newReader() throws IOException
	{
		// TODO Pipe
		return null;
//		ConfigAuditReactive.config.push();
//		ConfigAuditReactive.off.commit();
//		FileInputStream in = new FileInputStream(getFileIn());
//		Reader reader=new InputStreamReader(in);
//		ConfigAuditReactive.config.pop();
//		return new BufferedReader(reader);
	}
	@Test
	public void derived() throws IOException
	{
		class Derived extends OutputStreamWriter
		{
			Derived() throws IOException
			{
				super(new ByteArrayOutputStream(10));
			}
		};
		new Derived();
	}
}
