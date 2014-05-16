package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.ConfigAuditReactive;

import java.io.*;
import static com.octo.reactive.audit.TestTools.*;

/**
 * Created by pprados on 06/05/14.
 */
public class PushbackReaderTest extends FilterReaderTest
{
	@Override
	protected Reader newReader() throws IOException
	{
		push();
		Reader reader=super.newReader();
		pop();
		return new PushbackReader(reader);
	}
}
