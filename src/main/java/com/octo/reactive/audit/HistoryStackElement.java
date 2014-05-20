package com.octo.reactive.audit;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by pprados on 19/05/2014.
 */
class HistoryStackElement
{
	private Set<StackTraceElement> history = new HashSet<>();
	boolean addNewCaller(StackTraceElement stackElement)
	{
		return history.add(stackElement);
	}

}
