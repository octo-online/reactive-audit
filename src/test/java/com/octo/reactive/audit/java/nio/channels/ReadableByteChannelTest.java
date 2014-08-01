package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

@RunWith(Parameterized.class)
public class ReadableByteChannelTest
{
	private final Supplier<ReadableByteChannel> channel;

	public ReadableByteChannelTest(Supplier<ReadableByteChannel> channel)
	{
		this.channel = channel;
	}

	@Parameters
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
		try (ReadableByteChannel r = channel.get())
		{
			AuditReactive.strict.commit();
			r.read(buf);
		}
	}
}
