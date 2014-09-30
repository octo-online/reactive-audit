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

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class BlockingDequeTest
{
	@Test(expected = CPUReactiveAuditException.class)
	public void poll()
	{
		TestTools.strict.commit();
		BlockingDeque<Object> q = new LinkedBlockingDeque<>();
		q.poll();
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void pool_tu()
			throws InterruptedException
	{
		TestTools.strict.commit();
		BlockingDeque<Object> q = new LinkedBlockingDeque<>();
		q.poll(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void poolFirst()
			throws InterruptedException
	{
		TestTools.strict.commit();
		BlockingDeque<Object> q = new LinkedBlockingDeque<>();
		q.pollFirst(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void poolLast()
			throws InterruptedException
	{
		TestTools.strict.commit();
		BlockingDeque<Object> q = new LinkedBlockingDeque<>();
		q.pollLast(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void put()
			throws InterruptedException
	{
		TestTools.strict.commit();
		BlockingDeque<Object> q = new LinkedBlockingDeque<>();
		q.put("");
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void putFirst()
			throws InterruptedException
	{
		TestTools.strict.commit();
		BlockingDeque<Object> q = new LinkedBlockingDeque<>();
		q.putFirst("");
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void putLast()
			throws InterruptedException
	{
		TestTools.strict.commit();
		BlockingDeque<Object> q = new LinkedBlockingDeque<>();
		q.putLast("");
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void take()
			throws InterruptedException
	{
		BlockingDeque<Object> q = new LinkedBlockingDeque<>();
		q.take();
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void takeFirst()
			throws InterruptedException
	{
		BlockingDeque<Object> q = new LinkedBlockingDeque<>();
		q.takeFirst();
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void takeLast()
			throws InterruptedException
	{
		BlockingDeque<Object> q = new LinkedBlockingDeque<>();
		q.takeLast();
	}
}
