package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class DatagramSocketTest
{

	private static final int PORT = 8080;

	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws IOException
	{
		AuditReactive.off.commit();
		try (DatagramSocket socket = new DatagramSocket())
		{
			InetSocketAddress localhost = new InetSocketAddress("localhost", PORT);
			AuditReactive.strict.commit();
			socket.connect(localhost);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void receive() throws IOException
	{
		AuditReactive.off.commit();
		try (DatagramSocket socket = new DatagramSocket())
		{
			new InetSocketAddress("localhost", PORT);
			AuditReactive.strict.commit();
			DatagramPacket packet = new DatagramPacket(new byte[10], 10);
			socket.receive(packet);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void send() throws IOException
	{
		AuditReactive.off.commit();
		try (DatagramSocket socket = new DatagramSocket())
		{
			new InetSocketAddress("localhost", PORT);
			AuditReactive.strict.commit();
			DatagramPacket packet = new DatagramPacket(new byte[10], 10);
			socket.send(packet);
		}
	}
}
