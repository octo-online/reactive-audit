package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by pprados on 18/06/2014.
 */
public class ServerSocketChannelTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void accept() throws IOException
	{
		try (ServerSocketChannel r = ServerSocketChannel.open())
		{
			ConfigAuditReactive.strict.commit();
			r.accept();
		}
	}
}
