package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.AuditReactiveException;
import org.junit.Test;

import java.io.Console;

/**
 * Created by pprados on 14/05/2014.
 */
public class ConsoleTest
{
	@Test(expected=AuditReactiveException.class)
	public void readLine()
	{
		if (System.console()!=null)
			System.console().readLine();
		else
			throw new AuditReactiveException("No console");
	}
	@Test(expected=AuditReactiveException.class)
	public void readLine_String()
	{
		if (System.console()!=null)
			System.console().readLine("","");
		else
			throw new AuditReactiveException("No console");
	}
	@Test(expected=AuditReactiveException.class)
	public void readPassword()
	{
		if (System.console()!=null)
			System.console().readPassword();
		else
			throw new AuditReactiveException("No console");
	}
	@Test(expected=AuditReactiveException.class)
	public void readPassword_String()
	{
		if (System.console()!=null)
			System.console().readPassword("", "");
		else
			throw new AuditReactiveException("No console");
	}

}
