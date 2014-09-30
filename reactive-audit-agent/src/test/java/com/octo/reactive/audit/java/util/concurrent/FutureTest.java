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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("NullableProblems")
public class FutureTest
{
	private final Future<Object> f = new Future<Object>()
	{

		@Override
		public boolean cancel(boolean mayInterruptIfRunning)
		{
			return false;
		}

		@Override
		public boolean isCancelled()
		{
			return false;
		}

		@Override
		public boolean isDone()
		{
			return false;
		}

		@Override
		public Object get()
				throws InterruptedException, ExecutionException
		{
			return null;
		}

		@Override
		public Object get(long timeout, TimeUnit unit)
				throws InterruptedException, ExecutionException, TimeoutException
		{
			return null;
		}
	};

	@Test(expected = CPUReactiveAuditException.class)
	public void get()
			throws InterruptedException, ExecutionException
	{
		TestTools.strict.commit();
		f.get();
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void get_to()
			throws InterruptedException, ExecutionException, TimeoutException
	{
		TestTools.strict.commit();
		f.get(1, TimeUnit.MILLISECONDS);
	}
}
