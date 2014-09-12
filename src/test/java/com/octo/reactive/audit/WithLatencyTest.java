package com.octo.reactive.audit;

import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.WithLatency;
import org.junit.Test;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static com.octo.reactive.audit.lib.Latency.LOW;
import static com.octo.reactive.audit.lib.Latency.MEDIUM;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class WithLatencyTest
{
	@Test
	public void invokeWithLatencyLow()
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
        try
        {
            new Test().directCall();
        } catch (AuditReactiveException e)
        {
            assertEquals(LOW,e.getLatency());
        }
	}

	@Test
	public void invokeWithLatencyMedium()
	{
		AuditReactive.strict.commit();
		class Test
		{
			@WithLatency(Latency.MEDIUM)
			public void directCall()
			{
				fail();
			}
		}
        try
        {
		    new Test().directCall();
        } catch (AuditReactiveException e)
        {
            assertEquals(MEDIUM,e.getLatency());
        }
	}

	@Test
	public void invokeWithLatencyHigh()
	{
		AuditReactive.strict.commit();
		class Test
		{
			@WithLatency(Latency.HIGH)
			public void directCall()
			{
				fail();
			}
		}
        try
        {
		    new Test().directCall();
        } catch (AuditReactiveException e)
        {
            assertEquals(HIGH,e.getLatency());
        }
	}
}
