package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.Channel;
import java.util.function.Supplier;

/**
 * Created by pprados on 18/06/2014.
 */
public class ChannelTest
{
	Supplier<Channel> channel = IOTestTools::getInputFileChannel;

	@Test
	public void close() throws IOException
	{
		try (Channel w = channel.get())
		{
			AuditReactive.strict.commit();
			w.close();
		}
	}

}
