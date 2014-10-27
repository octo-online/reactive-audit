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

import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.ReactiveAuditException;
import com.octo.reactive.audit.lib.WithLatency;
import org.junit.Test;

import static com.octo.reactive.audit.lib.Latency.HIGH;
import static org.junit.Assert.*;

public class StartTest
{
	@Test(expected = ReactiveAuditException.class)
	public void invokeWithStart()
			throws InterruptedException
	{
		TestTools.strict.commit();
		ReactiveAudit.config.setBootStrapMode(ReactiveAudit.BootStrapMode.ANNOTATION);
		class Test
		{
			@com.octo.reactive.audit.lib.StartAudit
			public void start()
			{

			}
		}
		try
		{
			try
			{
				Thread.sleep(1);
			}
			catch (Exception e)
			{
				assertFalse(true);
			}
			new Test().start();
			Thread.sleep(1);
		}
		finally
		{
			ReactiveAudit.config.setStarted(true);
		}
	}

	@Test(expected = ReactiveAuditException.class)
	public void invokeWithLatencyHigh()
	{
        TestTools.strict.commit();
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
		}
		catch (ReactiveAuditException e)
		{
			assertEquals(HIGH, e.getLatency());
            throw e;
		}
	}
}
