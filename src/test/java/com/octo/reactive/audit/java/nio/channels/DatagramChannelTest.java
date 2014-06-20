package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by pprados on 18/06/2014.
 */
@RunWith(Parameterized.class)
public class DatagramChannelTest
{
	Supplier<DatagramChannel> datagramChannel;

	public DatagramChannelTest(Supplier<DatagramChannel> datagramChannel)
	{
		this.datagramChannel = datagramChannel;
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
	public void read() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		buf.clear();
		try (DatagramChannel w = datagramChannel.get())
		{
			ConfigAuditReactive.strict.commit();
			ByteBuffer[] srcs = {buf};
			w.read(srcs);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void write() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = datagramChannel.get())
		{
			ByteBuffer[] srcs = {buf};
			ConfigAuditReactive.strict.commit();
			w.write(srcs);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = datagramChannel.get())
		{
			ByteBuffer[] srcs = {buf};
			ConfigAuditReactive.strict.commit();
			w.connect(null);
		}
	}
}
