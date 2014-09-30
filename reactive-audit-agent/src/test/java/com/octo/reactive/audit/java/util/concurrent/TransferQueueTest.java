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

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

@SuppressWarnings("NullableProblems")
public class TransferQueueTest
{
	private final TransferQueue<Object> t = new TransferQueue<Object>()
	{

		@Override
		public boolean tryTransfer(Object o)
		{
			return false;
		}

		@Override
		public void transfer(Object o)
				throws InterruptedException
		{

		}

		@Override
		public boolean tryTransfer(Object o, long timeout, TimeUnit unit)
				throws InterruptedException
		{
			return false;
		}

		@Override
		public boolean hasWaitingConsumer()
		{
			return false;
		}

		@Override
		public int getWaitingConsumerCount()
		{
			return 0;
		}

		@Override
		public boolean add(Object o)
		{
			return false;
		}

		@Override
		public boolean offer(Object o)
		{
			return false;
		}

		@Override
		public void put(Object o)
				throws InterruptedException
		{

		}

		@Override
		public boolean offer(Object o, long l, TimeUnit timeUnit)
				throws InterruptedException
		{
			return false;
		}

		@Override
		public Object take()
				throws InterruptedException
		{
			return null;
		}

		@Override
		public Object poll(long l, TimeUnit timeUnit)
				throws InterruptedException
		{
			return null;
		}

		@Override
		public int remainingCapacity()
		{
			return 0;
		}

		@Override
		public boolean remove(Object o)
		{
			return false;
		}

		@Override
		public boolean contains(Object o)
		{
			return false;
		}

		@Override
		public int drainTo(Collection<? super Object> objects)
		{
			return 0;
		}

		@Override
		public int drainTo(Collection<? super Object> objects, int i)
		{
			return 0;
		}

		@Override
		public Object remove()
		{
			return null;
		}

		@Override
		public Object poll()
		{
			return null;
		}

		@Override
		public Object element()
		{
			return null;
		}

		@Override
		public Object peek()
		{
			return null;
		}

		@Override
		public int size()
		{
			return 0;
		}

		@Override
		public boolean isEmpty()
		{
			return false;
		}

		@SuppressWarnings("ConstantConditions")
		@Override
		public Iterator<Object> iterator()
		{
			return null;
		}

		@Override
		public Object[] toArray()
		{
			return new Object[0];
		}

		@SuppressWarnings("ConstantConditions")
		@Override
		public <T> T[] toArray(T[] a)
		{
			return null;
		}

		@Override
		public boolean containsAll(Collection<?> c)
		{
			return false;
		}

		@Override
		public boolean addAll(Collection<?> c)
		{
			return false;
		}

		@Override
		public boolean removeAll(Collection<?> c)
		{
			return false;
		}

		@Override
		public boolean retainAll(Collection<?> c)
		{
			return false;
		}

		@Override
		public void clear()
		{

		}
	};

	@Test(expected = CPUReactiveAuditException.class)
	public void transfer()
			throws InterruptedException
	{
		TestTools.strict.commit();
		t.transfer("");
	}

	@Test(expected = CPUReactiveAuditException.class)
	public void tryTransfer()
			throws InterruptedException
	{
		TestTools.strict.commit();
		t.tryTransfer("", 1, TimeUnit.MILLISECONDS);
	}
}
