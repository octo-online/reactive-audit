package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.function.Supplier;

public class FileChannelTest
{
	private final Supplier<FileChannel> readChannel  = IOTestTools::getInputFileChannel;
	private final Supplier<FileChannel> writeChannel = IOTestTools::getOutputFileChannel;

	@Test(expected = FileAuditReactiveException.class)
	public void open() throws IOException
	{
		AuditReactive.strict.commit();
		FileChannel.open(IOTestTools.getTempPath());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void transferFrom() throws IOException
	{
		try (FileChannel s = readChannel.get();
		     FileChannel d = writeChannel.get())
		{
			AuditReactive.strict.commit();
			d.transferFrom(s, 0, 1);
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void transferTo() throws IOException
	{
		try (FileChannel s = readChannel.get();
		     FileChannel d = writeChannel.get())
		{
			AuditReactive.strict.commit();
			s.transferTo(0, 1, d);
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void lock() throws IOException
	{
		try (FileChannel s = readChannel.get())
		{
			AuditReactive.strict.commit();
			s.lock();
		}
	}

}
