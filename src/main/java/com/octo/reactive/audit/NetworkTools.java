package com.octo.reactive.audit;

import java.lang.reflect.Field;
import java.net.URLConnection;

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
			return (Boolean) fieldConnected.get(conn);
		}
		catch (IllegalAccessException e)
		{
			throw new Error(e);
		}
	}
}
