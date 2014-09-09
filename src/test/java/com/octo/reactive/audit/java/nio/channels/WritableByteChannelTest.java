package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

@RunWith(Parameterized.class)
public class WritableByteChannelTest
{
	private final Supplier<WritableByteChannel> channel;

	public WritableByteChannelTest(Supplier<WritableByteChannel> channel)
	{
		this.channel = channel;
	}

	@Parameterized.Parameters
	public static Collection data() throws IOException
	{
		Supplier<?>[][] data = new Supplier<?>[][]
				{
						//Java8 {IOTestTools::getOutputFileChannel},
						{new Supplier<Object>()
						{
							@Override
							public Object get()
							{
								return IOTestTools.getOutputFileChannel();
							}
						}},
						//Jav8 {IOTestTools::getSocketChannel},
						{new Supplier<Object>()
						{
							@Override
							public Object get()
							{
								return IOTestTools.getSocketChannel();
							}
						}},
				};
		return Arrays.asList(data);
	}

	@Test(expected = AuditReactiveException.class)
	public void write() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (WritableByteChannel w = channel.get())
		{
			AuditReactive.strict.commit();
			w.write(buf);
		}
	}
}
