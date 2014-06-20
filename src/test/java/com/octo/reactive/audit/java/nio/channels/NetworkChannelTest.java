package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.NetworkChannel;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

import static com.octo.reactive.audit.IOTestTools.HOST;
import static com.octo.reactive.audit.IOTestTools.PORT;

/**
 * Created by pprados on 18/06/2014.
 */
public abstract class NetworkChannelTest
{
	Supplier<NetworkChannel> networkChannel;

	public NetworkChannelTest(Supplier<NetworkChannel> networkChannel)
	{
		this.networkChannel = networkChannel;
	}

	@Parameterized.Parameters
	public static Collection data() throws IOException
	{
		Supplier<?>[][] data = new Supplier<?>[][]
				{
						{IOTestTools::getSocketChannel},
						{IOTestTools::getDatagramChannel},
						{IOTestTools::getServerSocketChannel},
				};
		return Arrays.asList(data);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void bind() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (NetworkChannel r = networkChannel.get())
		{
			ConfigAuditReactive.strict.commit();
			SocketAddress add = new InetSocketAddress(HOST, PORT);
			r.bind(add);
		}
	}
}
