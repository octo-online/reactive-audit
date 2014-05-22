package com.octo.reactive.audit;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by pprados on 19/05/2014.
 */
class HistoryStackElement
{
	static final String myPackage = HistoryStackElement.class.getPackage().getName();
	private ConfigAuditReactive config;
	private Set<StackTraceElement>          history = new HashSet<>();
	private LinkedList<StackTraceElement[]> logged  = new LinkedList<>();

	HistoryStackElement(ConfigAuditReactive config)
	{
		this.config = config;
	}

	boolean addNewCaller(StackTraceElement stackElement)
	{
		return history.add(stackElement);
	}

	private void dumpStack(StackTraceElement[] stack)
	{
		System.err.println("STACK:");
		for (StackTraceElement s : stack)
		{
			System.err.println(s);
		}
		System.err.println("");
	}

	boolean isAlreadyLogged()
	{
		StackTraceElement[] stack = new Throwable().getStackTrace();
		StackTraceElement top=null;
		if (true)
		{
			for (StackTraceElement caller : stack)
			{
				if (!caller.getClassName().startsWith(myPackage)
						|| caller.getClassName().endsWith("Test")) // Pour les tests interne
				{
					top=caller;
					break;
				}
			}
		}
		boolean alreadyLogged = searchStack(stack);
		if (!alreadyLogged)
		{
			logged.add(stack);
			System.err.println("ADD "+top);
		}
		return alreadyLogged;
	}

	/**
	 * Search stack in history.
	 * @param stack
	 * @return true if found the same stack
	 */
	private boolean searchStack(StackTraceElement[] stack)
	{
		for (StackTraceElement[] s : logged)
		{
			if (stack.length == s.length)
			{
				boolean find = true;
				for (int i = 0; i < stack.length; ++i)
					if (!stack[i].equals(s[i]))
					{
						config.logger.debug("DIFFERENCE=" + stack[i] + " " + s[i]);
						find = false;
						break;
					}
				if (find) return true;
			}
		}
		return false;
	}
}
