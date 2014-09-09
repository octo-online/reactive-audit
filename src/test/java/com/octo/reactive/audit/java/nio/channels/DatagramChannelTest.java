package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.function.Supplier;

import static com.octo.reactive.audit.IOTestTools.HOST;
import static com.octo.reactive.audit.IOTestTools.PORT;

public class DatagramChannelTest
{
	// Java8 private final Supplier<DatagramChannel> channel = IOTestTools::getDatagramChannel;
	private final Supplier<DatagramChannel> channel =
			new Supplier<DatagramChannel>()
			{
				@Override
				public DatagramChannel get()
				{
					return IOTestTools.getDatagramChannel();
				}

			};

	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws IOException
	{
		try (DatagramChannel w = channel.get())
		{
			AuditReactive.strict.commit();
			w.connect(null);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void receive_b() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			AuditReactive.strict.commit();
			w.receive(buf);
		}
	}

	@Test
	public void receive_nb() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			w.configureBlocking(false);
			AuditReactive.strict.commit();
			w.receive(buf);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void send_b() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			AuditReactive.strict.commit();
			w.send(buf, new InetSocketAddress(HOST, PORT));
		}
	}

	@Test
	public void send_nb() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			w.configureBlocking(false);
			AuditReactive.strict.commit();
			w.send(buf, new InetSocketAddress(HOST, PORT));
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void read_b() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			AuditReactive.strict.commit();
			w.read(buf);
		}
	}

	@Test(expected = java.nio.channels.NotYetConnectedException.class)
	public void read_nb() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			w.configureBlocking(false);
			AuditReactive.strict.commit();
			w.read(buf);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void write_b() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			AuditReactive.strict.commit();
			w.read(buf);
		}
	}

	@Test(expected = java.nio.channels.NotYetConnectedException.class)
	public void write_nb() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			w.configureBlocking(false);
			AuditReactive.strict.commit();
			w.read(buf);
		}
	}
}
