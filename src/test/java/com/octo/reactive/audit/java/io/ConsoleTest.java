package com.octo.reactive.audit.java.io;

import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import com.octo.reactive.audit.lib.Latency;
import org.junit.Test;

public class ConsoleTest
{
	@Test(expected = AuditReactiveException.class)
	public void readLine()
	{
		if (System.console() != null)
			System.console().readLine();
		else
			throw new FileAuditReactiveException(Latency.LOW, "No console");
	}

	@Test(expected = AuditReactiveException.class)
	public void readLine_String()
	{
		if (System.console() != null)
			System.console().readLine("", "");
		else
			throw new FileAuditReactiveException(Latency.LOW, "No console");
	}

	@Test(expected = AuditReactiveException.class)
	public void readPassword()
	{
		if (System.console() != null)
			System.console().readPassword();
		else
			throw new FileAuditReactiveException(Latency.LOW, "No console");
	}

	@Test(expected = AuditReactiveException.class)
	public void readPassword_String()
	{
		if (System.console() != null)
			System.console().readPassword("", "");
		else
			throw new FileAuditReactiveException(Latency.LOW, "No console");
	}

}
