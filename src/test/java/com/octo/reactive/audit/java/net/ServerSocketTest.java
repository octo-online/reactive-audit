package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by pprados on 06/05/14.
 */
public class ServerSocketTest
{
	private static final int PORT = 12345;

	@Test(expected = NetworkAuditReactiveException.class)
	public void accept() throws IOException
	{
		ConfigAuditReactive.off.commit();
		try (ServerSocket socket = new ServerSocket(PORT))
		{
			ConfigAuditReactive.strict.commit();
			socket.accept();
		}
	}
}
