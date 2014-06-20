package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by pprados on 18/06/2014.
 */
@RunWith(Parameterized.class)
public class GatheringByteChannelTest
{
	Supplier<GatheringByteChannel> gatheringByteChannel;

	public GatheringByteChannelTest(Supplier<GatheringByteChannel> gatheringByteChannel)
	{
		this.gatheringByteChannel = gatheringByteChannel;
	}

	@Parameterized.Parameters
	public static Collection data() throws IOException
	{
		Supplier<?>[][] data = new Supplier<?>[][]
				{
						{IOTestTools::getOutputFileChannel}
				};
		return Arrays.asList(data);
	}

	@Test(expected = FileAuditReactiveException.class)
	public void write() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (GatheringByteChannel w = gatheringByteChannel.get())
		{
			ConfigAuditReactive.strict.commit();
			ByteBuffer[] srcs = {buf};
			w.write(srcs);
		}
	}
}
