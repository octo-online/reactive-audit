package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.util.function.Supplier;

/**
 * Created by pprados on 18/06/2014.
 */
public class GatheringByteChannelTest
{
	Supplier<GatheringByteChannel> channel = IOTestTools::getOutputFileChannel;

	@Test(expected = FileAuditReactiveException.class)
	public void write() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (GatheringByteChannel w = channel.get())
		{
			ConfigAuditReactive.strict.commit();
			ByteBuffer[] srcs = {buf};
			w.write(srcs);
		}
	}
}
