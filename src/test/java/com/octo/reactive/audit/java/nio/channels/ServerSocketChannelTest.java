package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

public class ServerSocketChannelTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void accept() throws IOException
	{
		try (ServerSocketChannel r = ServerSocketChannel.open())
		{
			AuditReactive.strict.commit();
			r.accept();
		}
	}
}
