package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Created by pprados on 06/05/14.
 */
public class DatagramSocketTest
{

	public static final int PORT = 8080;

	@Test(expected = NetworkAuditReactiveException.class)
	public void connect() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (DatagramSocket socket = new DatagramSocket())
		{
			InetSocketAddress localhost = new InetSocketAddress("localhost", PORT);
			ConfigAuditReactive.strict.commit();
			socket.connect(localhost);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void receive() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (DatagramSocket socket = new DatagramSocket())
		{
			InetSocketAddress localhost = new InetSocketAddress("localhost", PORT);
			ConfigAuditReactive.strict.commit();
			DatagramPacket packet = new DatagramPacket(new byte[10], 10);
			socket.receive(packet);
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void send() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (DatagramSocket socket = new DatagramSocket())
		{
			InetSocketAddress localhost = new InetSocketAddress("localhost", PORT);
			ConfigAuditReactive.strict.commit();
			DatagramPacket packet = new DatagramPacket(new byte[10], 10);
			socket.send(packet);
		}
	}
}
