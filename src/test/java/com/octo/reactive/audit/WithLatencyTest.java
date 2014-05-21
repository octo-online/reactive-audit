package com.octo.reactive.audit;

import org.junit.Test;

import static com.octo.reactive.audit.Latency.LOW;
import static org.junit.Assert.assertTrue;

/**
 * Created by pprados on 09/05/2014.
 */
public class WithLatencyTest
{
	@Test(expected=AuditReactiveException.class)
	public void invokeWithLatency()
	{
		ConfigAuditReactive.strict.commit();
		class Test
		{
			@WithLatency(LOW)
			public void directCall()
			{
				assertTrue(false);
			}
		}
		new Test().directCall();
	}
}
