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

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DebugAspect
{
	final protected ReactiveAudit config = ReactiveAudit.config;

	/*
		@Before("call(java.nio.channel.SocketChannel.new(..))")
		public void socketChannel_new(JoinPoint thisJoinPoint)
		{
		config.logger.finest(thisJoinPoint.toString());
		}
	*/
	/*
	@Before("call(* java.nio.channel.SocketChannel.*(..))")
	public void socketChannel_all(JoinPoint thisJoinPoint)
	{
		config.logger.finest(thisJoinPoint.toString());
	}
	*/
	/*
	@Before("call(* java.nio.channels.ServerSocketChannel.*(..))")
	public void serverSocketChannel_all(JoinPoint thisJoinPoint)
	{
		config.logger.finest(thisJoinPoint.toString());
	}
	*/
/*
	@Before("call(java.nio.channels.NetworkChannel.new(..))")
	public void networkChannel_new(JoinPoint thisJoinPoint)
	{
		config.logger.finest(thisJoinPoint.toString());
	}
*/
	/*
	@Before("call(* java.nio.channels.NetworkChannel.*(..))")
	public void networkChannel_all(JoinPoint thisJoinPoint)
	{
		config.logger.finest(thisJoinPoint.toString());
	}
*/
	@Before("call(* java.nio.Buffer.*(..))")
	public void buffer_all(JoinPoint thisJoinPoint)
	{
		config.logger.finest(thisJoinPoint.toString());
	}
	/*
	@Before("call(java.nio.channels.AsynchronousChannel.new(..))")
	public void asynchronousChannel_new(JoinPoint thisJoinPoint)
	{
		config.logger.finest(thisJoinPoint.toString());
	}
	*/
	/*
	@Before("call(* java.nio.channels.AsynchronousChannel.*(..))")
	public void asynchronousChannel_all(JoinPoint thisJoinPoint)
	{
		config.logger.finest(thisJoinPoint.toString());
	}
	*/
}
