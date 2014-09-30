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

package com.octo.reactive.audit.java.util.concurrent;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.CPUReactiveAuditException;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest
{
	@Test(expected = CPUReactiveAuditException.class)
	public void await()
			throws InterruptedException
	{
		TestTools.strict.commit();
		CountDownLatch c = new CountDownLatch(1);
		c.await();
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void await_to()
			throws InterruptedException
	{
		TestTools.strict.commit();
		CountDownLatch c = new CountDownLatch(1);
		c.await(1, TimeUnit.MILLISECONDS);
	}
}
