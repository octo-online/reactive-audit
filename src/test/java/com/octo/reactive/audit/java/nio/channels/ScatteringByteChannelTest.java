package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ScatteringByteChannel;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by pprados on 18/06/2014.
 */
@RunWith(Parameterized.class)
public class ScatteringByteChannelTest
{
	Supplier<ScatteringByteChannel> channel;

	public ScatteringByteChannelTest(Supplier<ScatteringByteChannel> channel)
	{
		this.channel = channel;
	}

	@Parameterized.Parameters
	public static Collection data() throws IOException
	{
		Supplier<?>[][] data = new Supplier<?>[][]
				{
						{IOTestTools::getInputFileChannel},
						{IOTestTools::getSocketChannel},
				};
		return Arrays.asList(data);
	}

	@Test(expected = AuditReactiveException.class)
	public void read() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (ScatteringByteChannel r = channel.get())
		{
			ConfigAuditReactive.strict.commit();
			ByteBuffer[] srcs = {buf};
			r.read(srcs);
		}
	}

	@Test(expected = AuditReactiveException.class)
	public void read_ii() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (ScatteringByteChannel r = channel.get())
		{
			ConfigAuditReactive.strict.commit();
			ByteBuffer[] srcs = {buf};
			r.read(srcs, 0, 1);
		}
	}
}
