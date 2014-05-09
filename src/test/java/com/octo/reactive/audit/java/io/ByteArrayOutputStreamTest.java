package com.octo.reactive.audit.java.io;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class ByteArrayOutputStreamTest extends OutputStreamTest
{
	protected OutputStream newOutputStream() throws IOException
	{
		return new ByteArrayOutputStream();
	}

}
