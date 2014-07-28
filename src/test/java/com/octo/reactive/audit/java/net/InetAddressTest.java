package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;

public class InetAddressTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void getAllByName() throws IOException
	{
		InetAddress.getAllByName("localhost");
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getByName() throws IOException
	{
		InetAddress.getByName("localhost");
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void getHostName() throws IOException
	{
		InetAddress.getLocalHost().getHostName();
	}
}
