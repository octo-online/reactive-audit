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

import java.lang.reflect.Field;
import java.net.URLConnection;

public final class NetworkTools
{
	private static final Field fieldConnected;

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
