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

import com.octo.reactive.audit.lib.TolerateLatency;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TolerateLatencyTest
{
	@Test
	@TolerateLatency("Direct call")
	public void directCall()
	{
		assertTrue(ReactiveAudit.config.isSuppressAudit());
	}

	@Test
	@TolerateLatency("Indirect call")
	public void indirectCall()
	{
		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				assertTrue(ReactiveAudit.config.isSuppressAudit());
				assertEquals(1, ReactiveAudit.config.getSuppress());
			}
		};
		r.run();
		assertTrue(ReactiveAudit.config.isSuppressAudit());
		assertEquals(1, ReactiveAudit.config.getSuppress());
	}

	@Test
	@TolerateLatency("Double call")
	public void doubleCall()
	{
		Runnable r = new Runnable()
		{
			@TolerateLatency
			@Override
			public void run()
			{
				assertTrue(ReactiveAudit.config.isSuppressAudit());
				assertEquals(2, ReactiveAudit.config.getSuppress());
			}
		};
		r.run();
		assertEquals(1, ReactiveAudit.config.getSuppress());
	}
}
