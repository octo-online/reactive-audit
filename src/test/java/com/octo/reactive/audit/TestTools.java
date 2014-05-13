package com.octo.reactive.audit;

/**
 * Created by pprados on 12/05/2014.
 */
public class TestTools
{
	public static void push()
	{
		ConfigAuditReactive.config.push();
		ConfigAuditReactive.off.commit();
	}
	public static void pop()
	{
		ConfigAuditReactive.config.pop();
	}
}
