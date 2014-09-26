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

import com.octo.reactive.audit.lib.FileReactiveAuditException;
import com.octo.reactive.audit.lib.Latency;
import com.octo.reactive.audit.lib.NetworkReactiveAuditException;
import org.aspectj.lang.JoinPoint;

public final class FactoryException
{
	private FactoryException()
	{
	}

	public static NetworkReactiveAuditException newNetwork(Latency latency, JoinPoint thisJoinPoint)
	{
		return newNetwork(latency, thisJoinPoint, null);
	}

	public static NetworkReactiveAuditException newNetwork(Latency latency, JoinPoint thisJoinPoint, CharSequence msg)
	{
		return new NetworkReactiveAuditException(latency,
												 (msg == null)
												 ? thisJoinPoint.getSignature().toString()
												 : thisJoinPoint.getSignature().toString() + " " + msg);
	}

	public static FileReactiveAuditException newFile(Latency latency, JoinPoint thisJoinPoint)
	{
		return newFile(latency, thisJoinPoint, null);
	}

	public static FileReactiveAuditException newFile(Latency latency, JoinPoint thisJoinPoint, CharSequence msg)
	{
		return new FileReactiveAuditException(latency, (msg == null)
													   ? thisJoinPoint.getSignature().toString()
													   : thisJoinPoint.getSignature().toString() + " " + msg);
	}
}
