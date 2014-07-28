package com.octo.reactive.audit;

/**
 * Helper for unit test.
 */
public class TestTools
{
	public static void push()
	{
		AuditReactive.config.push();
		AuditReactive.off.commit();
	}

	public static void pop()
	{
		AuditReactive.config.pop();
	}
}
