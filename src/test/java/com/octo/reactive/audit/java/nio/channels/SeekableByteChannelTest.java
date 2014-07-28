package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.function.Supplier;

public class SeekableByteChannelTest
{
	Supplier<SeekableByteChannel> channel = IOTestTools::getInputFileChannel;

	@Test(expected = AuditReactiveException.class)
	public void position() throws IOException
	{
		try (SeekableByteChannel r = channel.get())
		{
			AuditReactive.strict.commit();
			r.position(0);
		}
	}

	@Test(expected = AuditReactiveException.class)
	public void truncate() throws IOException
	{
		try (SeekableByteChannel r = channel.get())
		{
			AuditReactive.strict.commit();
			r.truncate(0);
		}
	}
}
