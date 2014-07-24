package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by pprados on 19/05/2014.
 */
public class ProcessTest
{
	@Test(expected = AuditReactiveException.class)
	public void waitFor() throws IOException, InterruptedException
	{
		AuditReactive.strict.commit();
		Runtime.getRuntime().exec("java -version").waitFor();
	}
}
