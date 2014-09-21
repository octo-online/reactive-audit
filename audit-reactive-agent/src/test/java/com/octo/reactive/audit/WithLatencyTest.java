/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
