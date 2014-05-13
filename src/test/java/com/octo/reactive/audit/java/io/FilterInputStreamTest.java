package com.octo.reactive.audit.java.io;

import org.junit.Test;

import java.io.*;

/**
 * Created by pprados on 11/05/2014.
 */
public class FilterInputStreamTest extends FileInputStreamTest
{
	@Override
	protected InputStream newInputStream() throws IOException
	{
		return new BufferedInputStream(super.newInputStream());
	}

}
