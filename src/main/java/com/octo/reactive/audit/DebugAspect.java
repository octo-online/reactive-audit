package com.octo.reactive.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by pprados on 07/07/2014.
 */
@Aspect
public class DebugAspect
{
	protected AuditReactive config = AuditReactive.config;

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
