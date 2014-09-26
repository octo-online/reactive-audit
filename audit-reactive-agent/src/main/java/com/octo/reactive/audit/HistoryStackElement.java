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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class HistoryStackElement
{
	//	static final  String                     myPackage = HistoryStackElement.class.getPackage().getName();
	private final Set<HashStackTraceElement> logged = Collections.synchronizedSet(new HashSet<HashStackTraceElement>());


	void purge()
	{
		logged.clear();
	}

	// WARNING : use synchronized. Is it possible to reduce the blocking part ?
	boolean isAlreadyLogged(StackTraceElement[] stack)
	{
		HashStackTraceElement hse = new HashStackTraceElement(stack);
		// Ok, it's block the code, but for a small period
		synchronized (this)
		{
			// Detect with the precalculated hash value
			return !logged.add(hse);
		}
	}

	static class HashStackTraceElement
	{
		final long hash;
		//final StackTraceElement[] stack;

		public HashStackTraceElement(StackTraceElement[] aStack)
		{
			//stack = aStack;
			long aHash = 0;
			for (int i = 0; i < aStack.length; ++i)
				aHash = (aHash << 1) ^ aStack[i].hashCode();
			hash = aHash;
		}

		@Override
		public int hashCode()
		{
			return (int) hash;
		}

		@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
		@Override
		public boolean equals(Object obj)
		{
			// It's enough because the probability is very low to have 2 stack trace with the same hash value.
			return hash == ((HashStackTraceElement) obj).hash;
		}
	}

}
