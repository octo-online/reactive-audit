package com.octo.reactive.audit.java.lang;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;

/**
 * Created by pprados on 19/05/2014.
 */
public class ObjectTest
{
	@Test(expected = AuditReactiveException.class)
	public void wait_() throws InterruptedException
	{
		AuditReactive.strict.commit();
		Object x = new Object();
		synchronized (x)
		{
			x.wait(5);
		}
	}
}
