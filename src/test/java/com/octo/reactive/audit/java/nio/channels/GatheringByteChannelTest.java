package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.util.function.Supplier;

public class GatheringByteChannelTest
{
	//Java8 private final Supplier<GatheringByteChannel> channel = IOTestTools::getOutputFileChannel;
	private final Supplier<GatheringByteChannel> channel =
			new Supplier<GatheringByteChannel>()
			{
				@Override
				public GatheringByteChannel get()
				{
					return IOTestTools.getOutputFileChannel();
				}
			};

	@Test(expected = FileAuditReactiveException.class)
	public void write() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (GatheringByteChannel w = channel.get())
		{
			AuditReactive.strict.commit();
			ByteBuffer[] srcs = {buf};
			w.write(srcs);
		}
	}
}
