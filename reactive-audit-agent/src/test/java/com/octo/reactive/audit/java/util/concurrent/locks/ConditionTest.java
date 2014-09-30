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

package com.octo.reactive.audit.java.util.concurrent.locks;

import com.octo.reactive.audit.TestTools;
import com.octo.reactive.audit.lib.CPUReactiveAuditException;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

@SuppressWarnings("NullableProblems")
public class ConditionTest
{
	private final Condition c = new Condition()
	{

		@Override
		public void await()
				throws InterruptedException
		{

		}

		@Override
		public void awaitUninterruptibly()
		{

		}

		@Override
		public long awaitNanos(long l)
				throws InterruptedException
		{
			return 0;
		}

		@Override
		public boolean await(long l, TimeUnit timeUnit)
				throws InterruptedException
		{
			return false;
		}

		@Override
		public boolean awaitUntil(Date date)
				throws InterruptedException
		{
			return false;
		}

		@Override
		public void signal()
		{

		}

		@Override
		public void signalAll()
		{

		}
	};

	@Test(expected = CPUReactiveAuditException.class)
	public void await()
			throws InterruptedException
	{
        TestTools.strict.commit();
		c.await();
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void await_to()
			throws InterruptedException
	{
        TestTools.strict.commit();
		c.await(1, TimeUnit.MILLISECONDS);
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void awaitNanos()
			throws InterruptedException
	{
        TestTools.strict.commit();
		c.awaitNanos(1);
	}
}
