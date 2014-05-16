package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import org.junit.Test;

import java.io.*;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

/**
 * Created by pprados on 11/05/2014.
 */
public class FilterInputStreamTest extends FileInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		push();
		InputStream in = super.newInputStream();
		pop();
		return new BufferedInputStream(in);
	}
	@Override
	@Test
	public void New() throws IOException
	{
		super.New();
	}

}
