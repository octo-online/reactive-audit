package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.ConfigAuditReactive;
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

/**
 * Created by pprados on 18/06/2014.
 */
public class DatagramChannelTest
{
	Supplier<DatagramChannel> channel = IOTestTools::getDatagramChannel;

	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws IOException
	{
		try (DatagramChannel w = channel.get())
		{
			ConfigAuditReactive.strict.commit();
			w.connect(null);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void receive_b() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			ConfigAuditReactive.strict.commit();
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
			ConfigAuditReactive.strict.commit();
			w.receive(buf);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void send_b() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			ConfigAuditReactive.strict.commit();
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
			ConfigAuditReactive.strict.commit();
			w.send(buf, new InetSocketAddress(HOST, PORT));
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void read_b() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			ConfigAuditReactive.strict.commit();
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
			ConfigAuditReactive.strict.commit();
			w.read(buf);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void write_b() throws IOException
	{
		ByteBuffer buf = ByteBuffer.allocate(10);
		try (DatagramChannel w = channel.get())
		{
			ConfigAuditReactive.strict.commit();
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
			ConfigAuditReactive.strict.commit();
			w.read(buf);
		}
	}
}
