package com.octo.reactive.audit.java.net;

import java.lang.reflect.Field;
import java.net.URLConnection;

/**
 * Created by pprados on 28/05/2014.
 */
public final class NetworkTools
{
	static final Field fieldConnected;

	private NetworkTools()
	{

	}

	static
	{
		try
		{
			fieldConnected = URLConnection.class.getDeclaredField("connected");
			fieldConnected.setAccessible(true);
		}
		catch (NoSuchFieldException e)
		{
			throw new Error(e);
		}
	}

	static public boolean isURLConnected(URLConnection conn)
	{
		try
		{
			return (Boolean) fieldConnected.get(conn));
		}
		catch (IllegalAccessException e)
		{
			throw new Error(e);
		}
	}
}
