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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@SuppressWarnings("NullableProblems")
public class LockTest
{
	private final Lock c = new Lock()
	{

		@Override
		public void lock()
		{

		}

		@Override
		public void lockInterruptibly()
				throws InterruptedException
		{

		}

		@Override
		public boolean tryLock()
		{
			return false;
		}

		@Override
		public boolean tryLock(long l, TimeUnit timeUnit)
				throws InterruptedException
		{
			return false;
		}

		@Override
		public void unlock()
		{

		}

		@SuppressWarnings("ConstantConditions")
		@Override
		public Condition newCondition()
		{
			return null;
		}
	};

	@Test(expected = CPUReactiveAuditException.class)
	public void lock()
			throws InterruptedException
	{
        TestTools.strict.commit();
		c.lock();
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void lockInterruptibly()
			throws InterruptedException
	{
        TestTools.strict.commit();
		c.lockInterruptibly();
	}

}
