package com.octo.reactive.audit;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pprados on 19/05/2014.
 */
class HistoryStackElement
{
	static final String myPackage = HistoryStackElement.class.getPackage().getName();
	private ConfigAuditReactive config;
	private List<StackTraceElement[]> logged = Collections.synchronizedList(new LinkedList<>());

	HistoryStackElement(ConfigAuditReactive config)
	{
		this.config = config;
	}

	void purge()
	{
		logged.clear();
	}

	/*FIXME:sync?*//*synchronized*/ boolean isAlreadyLogged(StackTraceElement[] stack)
	{
		boolean alreadyLogged = searchStack(stack);
		if (!alreadyLogged)
		{
			logged.add(stack);
		}
		return alreadyLogged;
	}

	/**
	 * Search stack in history.
	 *
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
						//logger.debug("DIFFERENCE=" + stack[i] + " " + s[i]);
						find = false;
						break;
					}
				if (find) return true;
			}
		}
		return false;
	}
}
