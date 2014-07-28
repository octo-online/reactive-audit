package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.WithLatency;
import org.junit.Test;

import static com.octo.reactive.audit.lib.Latency.LOW;
import static org.junit.Assert.fail;

public class WithLatencyTest
{
	@Test(expected = AuditReactiveException.class)
	public void invokeWithLatency()
	{
		AuditReactive.strict.commit();
		class Test
		{
			@WithLatency(LOW)
			public void directCall()
			{
				fail();
			}
		}
		new Test().directCall();
	}
}
