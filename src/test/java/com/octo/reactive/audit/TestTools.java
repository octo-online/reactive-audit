package com.octo.reactive.audit;

/**
 * Helper for unit test.
 * Created by pprados on 12/05/2014.
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
