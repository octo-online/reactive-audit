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

package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.net.Socket;

import static com.octo.reactive.audit.lib.Latency.MEDIUM;

// Nb methods: 9
@Aspect
public class SocketAudit extends AbstractNetworkAudit
{

	@Before("call(java.net.Socket+.new(java.net.InetAddress,..))")
	public void new_1(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(java.net.Socket+.new(String,..))")
	public void new_2(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(void sendUrgentData(int))")
	public void sendUrgentData(JoinPoint thisJoinPoint)
	{
		latency(MEDIUM, thisJoinPoint);
	}

	@Before("call(void java.net.Socket.connect(..))")
	public void connect(JoinPoint thisJoinPoint)
	{
		Socket socket = (Socket) thisJoinPoint.getTarget();
		if (!socket.isConnected())
		{
			latency(MEDIUM, thisJoinPoint);
		}
	}

}
