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

package com.octo.reactive.audit.lib;

/**
 * Exception thrown by the JVM agent if a network blocking API is used.
 * This exception is thrown only if the throwExceptions parameter is true.
 *
 * @author Philippe PRADOS
 * @since 1.0
 */
public class NetworkReactiveAuditException extends ReactiveAuditException
{
	/**
	 * Create an {@link AssertionError} with {@link Latency}, thread name and message.
	 *
	 * @param latency The latency for this exception.
	 * @param message The message associated with this exception.
	 */
	public NetworkReactiveAuditException(Latency latency, String message)
	{
		super(latency, "Call method " + message);
	}

	/**
	 * Create an {@link AssertionError} with {@link Latency}, thread name and formatted message.
	 *
	 * @param latency The latency for this exception.
	 * @param format  The message format associated with this exception.
	 * @param args    The arguments to generate the formatted message.
	 */
	public NetworkReactiveAuditException(Latency latency, String format, Object... args)
	{
		super(latency, "Call method " + format, args);
	}
}
