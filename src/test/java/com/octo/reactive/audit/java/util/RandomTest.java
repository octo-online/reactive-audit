package com.octo.reactive.audit.java.util;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.CPUAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class RandomTest
{
	@Test(expected = CPUAuditReactiveException.class)
	public void nextBoolean() throws IOException
	{
		AuditReactive.strict.commit();
		Random r = new Random();
		r.nextBoolean();
	}

}
