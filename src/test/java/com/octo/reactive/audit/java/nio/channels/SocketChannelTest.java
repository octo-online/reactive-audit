package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import static com.octo.reactive.audit.IOTestTools.HOST;
import static com.octo.reactive.audit.IOTestTools.PORT;

/**
 * Created by pprados on 18/06/2014.
 */
public class SocketChannelTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws IOException
	{
		try (SocketChannel r = IOTestTools.getSocketChannel())
		{
			AuditReactive.strict.commit();
			r.connect(null);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void finishConnect() throws IOException
	{
		try (SocketChannel r = IOTestTools.getSocketChannel())
		{
			AuditReactive.strict.commit();
			r.finishConnect();
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void open() throws IOException
	{
		AuditReactive.strict.commit();
		SocketChannel.open(new InetSocketAddress(HOST, PORT));
	}
}
