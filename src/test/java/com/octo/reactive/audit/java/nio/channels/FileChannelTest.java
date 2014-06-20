package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.AuditReactiveException;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by pprados on 18/06/2014.
 */
@RunWith(Parameterized.class)
public class FileChannelTest
{
	Supplier<FileChannel> readChannel;
	Supplier<FileChannel> writeChannel;

	public FileChannelTest(Supplier<FileChannel> readChannel, Supplier<FileChannel> writeChannel)
	{
		this.readChannel = readChannel;
		this.writeChannel = writeChannel;
	}

	@Parameterized.Parameters
	public static Collection data() throws IOException
	{
		Supplier<?>[][] data = new Supplier<?>[][]
				{
						{IOTestTools::getInputFileChannel, IOTestTools::getOutputFileChannel}
				};
		return Arrays.asList(data);
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

	@Test(expected = AuditReactiveException.class)
	public void begin() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		class C extends AbstractInterruptibleChannel
		{

			@Override
			protected void implCloseChannel() throws IOException
			{

			}

			public void doBegin()
			{
				begin();
			}
		}
		new C().doBegin();
	}

}
