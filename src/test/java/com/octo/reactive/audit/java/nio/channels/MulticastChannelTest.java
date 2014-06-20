package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.nio.channels.MulticastChannel;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by pprados on 18/06/2014.
 */
@RunWith(Parameterized.class)
public class MulticastChannelTest
{
	Supplier<MulticastChannel> multicastChannel;

	public MulticastChannelTest(Supplier<MulticastChannel> multicastChannel)
	{
		this.multicastChannel = multicastChannel;
	}

	@Parameterized.Parameters
	public static Collection data() throws IOException
	{
		Supplier<?>[][] data = new Supplier<?>[][]
				{
						{IOTestTools::getDatagramChannel}
				};
		return Arrays.asList(data);
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void join() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (MulticastChannel r = multicastChannel.get())
		{
			ConfigAuditReactive.strict.commit();
			NetworkInterface ni = IOTestTools.getNetworkInterface();
			InetAddress group = InetAddress.getByName("225.4.5.6");
			r.join(group, ni);
		}
	}
}
