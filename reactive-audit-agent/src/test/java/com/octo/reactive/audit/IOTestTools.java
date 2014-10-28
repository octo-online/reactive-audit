/*
 * Copyright 2014 OCTO Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.octo.reactive.audit;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.channels.*;
import java.nio.file.Path;
import java.util.Enumeration;

import static com.octo.reactive.audit.TestTools.pop;
import static com.octo.reactive.audit.TestTools.push;
import static org.junit.Assert.fail;

@SuppressWarnings("ResultOfMethodCallIgnored")
public final class IOTestTools
{
	public static final  String HOST     = "www.google.com";
	public static final  int    PORT     = 80;
	private static final int    UDP_PORT = 5000;

	private IOTestTools()
	{
	}

	public static File getTempFile()
	{
		File f = null;
		push();
		try
		{
			f = File.createTempFile("temp-file-name", ".tmp");
			f.delete();
			f.deleteOnExit();
			f.createNewFile();
		}
		catch (IOException e)
		{
			fail(e.getMessage());
			e.printStackTrace();
		}
		pop();
		return f;
	}

	public static Path getTempPath()
	{
		return getTempFile().toPath();
	}

	public static FileInputStream getTempFileInputStream()
	{
		try
		{
			push();
			return new FileInputStream(getTempFile());
		}
		catch (IOException e)
		{
			fail(e.getMessage());
			e.printStackTrace();
			return null;
		}
		finally
		{
			pop();
		}
	}

	public static FileReader getTempFileReader()
	{
		try
		{
			push();
			return new FileReader(getTempFile());
		}
		catch (IOException e)
		{
			fail(e.getMessage());
			e.printStackTrace();
			return null;
		}
		finally
		{
			pop();
		}
	}

	public static FileOutputStream getTempFileOutputStream()
	{
		try
		{
			push();
			return new FileOutputStream(getTempFile());
		}
		catch (IOException e)
		{
			fail(e.getMessage());
			e.printStackTrace();
			return null;
		}
		finally
		{
			pop();
		}
	}

	public static FileWriter getTempFileWriter()
	{
		try
		{
			push();
			return new FileWriter(getTempFile());
		}
		catch (IOException e)
		{
			fail(e.getMessage());
			e.printStackTrace();
			return null;
		}
		finally
		{
			pop();
		}
	}

	public static DatagramChannel getDatagramChannel()
	{
		try
		{
			push();
			DatagramChannel channel = DatagramChannel.open();
			channel.socket().bind(new InetSocketAddress(UDP_PORT));
			//channel.connect(new InetSocketAddress(HOST,PORT));
			return channel;
		}
		catch (IOException e)
		{
			fail(e.getMessage());
			e.printStackTrace();
			return null;
		}
		finally
		{
			pop();
		}
	}

	public static FileChannel getOutputFileChannel()
	{
		return getTempFileOutputStream().getChannel();
	}

	public static FileChannel getInputFileChannel()
	{
		return getTempFileInputStream().getChannel();
	}

	public static SocketChannel getSocketChannel()
	{
		try
		{
			push();
			return SocketChannel.open(new InetSocketAddress(HOST, PORT));
		}
		catch (IOException e)
		{
			fail(e.getMessage());
			e.printStackTrace();
			return null;
		}
		finally
		{
			pop();
		}
	}

	public static ServerSocketChannel getServerSocketChannel()
	{
		try
		{
			push();
			return ServerSocketChannel.open();
		}
		catch (IOException e)
		{
			fail(e.getMessage());
			e.printStackTrace();
			return null;
		}
		finally
		{
			pop();
		}
	}

	public static SelectableChannel getSelectableChannel()
	{
		try
		{
			push();
			Selector selector = Selector.open();
			SelectableChannel channel = getSocketChannel();
			channel.configureBlocking(false);
			channel.register(selector, SelectionKey.OP_READ);
			return channel;
		}
		catch (IOException e)
		{
			fail(e.getMessage());
			e.printStackTrace();
			return null;
		}
		finally
		{
			pop();
		}
	}

	public static NetworkInterface getNetworkInterface()
	{
		try
		{
			NetworkInterface ni = null;
			for (Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
				 e.hasMoreElements(); )
			{
				NetworkInterface n = e.nextElement();
				if (n.isUp() && !n.isLoopback())
				{
					ni = n;
					break;
				}
			}
			assert ni != null : "no active network";
			return ni;
		}
		catch (IOException e)
		{
			fail(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

    private static volatile int network=-1;
    static public boolean isNetworkConnected()
    {
        if (network==-1) {
            try {
                ReactiveAudit.config.incSuppress();
                InetAddress.getByName("www.google.com");
                network = 1;
            } catch (UnknownHostException e) {
                network = 0;
            }
            finally
            {
                ReactiveAudit.config.decSuppress();
            }
        }
        return network==1;
    }
}
