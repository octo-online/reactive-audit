package com.octo.reactive.audit.java.io;

import java.io.*;

/**
 * Created by pprados on 06/05/14.
 */
public class ByteArrayInputStreamTest extends InputStreamTest
{
	protected InputStream newInputStream()
	{
		return new ByteArrayInputStream(new byte[1]);
	}

}
