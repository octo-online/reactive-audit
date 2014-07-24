package com.octo.reactive.audit;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pprados on 19/05/2014.
 */
class HistoryStackElement
{
	static final String myPackage = HistoryStackElement.class.getPackage().getName();
	private AuditReactive config;
	private Set<HashStackTraceElement> logged = Collections.synchronizedSet(new HashSet<>());

	HistoryStackElement(AuditReactive config)
	{
		this.config = config;
	}

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
		int                 hash;
		StackTraceElement[] stack;

		public HashStackTraceElement(StackTraceElement[] aStack)
		{
			stack = aStack;
			int aHash = 0;
			for (int i = 0; i < aStack.length; ++i)
				aHash ^= aStack[i].hashCode();
			hash = aHash;
		}

		@Override
		public int hashCode()
		{
			return hash;
		}

		@Override
		public boolean equals(Object obj)
		{
			// It's enough because the probability is very low to have 2 stack trace with the same hash value.
			return hash == ((HashStackTraceElement) obj).hash;
		}
	}

}
