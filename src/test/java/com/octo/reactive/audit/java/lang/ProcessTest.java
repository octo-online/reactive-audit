package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;

import java.io.IOException;

public class ProcessTest
{
	@Test(expected = AuditReactiveException.class)
	public void waitFor() throws IOException, InterruptedException
	{
		AuditReactive.strict.commit();
		Runtime.getRuntime().exec("java -version").waitFor();
	}
}
