package com.octo.reactive.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Created by pprados on 09/05/2014.
 */
@Aspect
public class MainAspect extends AbstractAudit
{
	@Around("execution(public static void main(java.lang.String[]))")
	public void startup(ProceedingJoinPoint thisJoinPoint/*,String[] args*/)
			throws Throwable
	{
		// FIXME
//		final Thread mainThread = Thread.currentThread();
//		Runtime.getRuntime().addShutdownHook(new Thread() {
//			@Override
//			public void run() {
//				//Thread.currentThread().setDaemon(true);
//				try
//				{
//					mainThread.join();
//				}
//				catch (InterruptedException e)
//				{
//					e.printStackTrace();
//				}
//				System.err.println("XXX SHUTDOWN"); // FIXME: C'est pas bon
//				ConfigAuditReactive.config.shutdown();
//			}
//		});
		ConfigAuditReactive.config.startup();
		thisJoinPoint.proceed();
	}
}
