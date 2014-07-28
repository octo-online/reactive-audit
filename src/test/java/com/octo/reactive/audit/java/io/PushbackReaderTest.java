package com.octo.reactive.audit.java.io;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;

public class PushbackReaderTest extends FilterReaderTest
{
	@Override
	protected Reader newReader() throws IOException
	{
		push();
		Reader reader = super.newReader();
		pop();
		return new PushbackReader(reader);
	}
}
