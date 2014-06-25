package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.function.Supplier;

/**
 * Created by pprados on 18/06/2014.
 */
public class FileChannelTest
{
	Supplier<FileChannel> readChannel  = IOTestTools::getInputFileChannel;
	Supplier<FileChannel> writeChannel = IOTestTools::getOutputFileChannel;

	@Test(expected = FileAuditReactiveException.class)
	public void open() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		FileChannel.open(IOTestTools.getTempPath());
	}

	@Test(expected = FileAuditReactiveException.class)
	public void transferFrom() throws IOException
	{
		try (FileChannel s = readChannel.get();
		     FileChannel d = writeChannel.get())
		{
			ConfigAuditReactive.strict.commit();
			d.transferFrom(s, 0, 1);
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void transferTo() throws IOException
	{
		try (FileChannel s = readChannel.get();
		     FileChannel d = writeChannel.get())
		{
			ConfigAuditReactive.strict.commit();
			s.transferTo(0, 1, d);
		}
	}

	@Test(expected = FileAuditReactiveException.class)
	public void lock() throws IOException
	{
		try (FileChannel s = readChannel.get())
		{
			ConfigAuditReactive.strict.commit();
			s.lock();
		}
	}

}
