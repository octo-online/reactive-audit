package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;

/**
 * Created by pprados on 19/05/2014.
 */
public class ReadableTest
{
	@Test(expected = AuditReactiveException.class)
	public void waitFor() throws IOException, InterruptedException
	{
		AuditReactive.strict.commit();
		Readable readable = new StringReader("");
		CharBuffer buf = CharBuffer.allocate(1);
		readable.read(buf);
	}
}
