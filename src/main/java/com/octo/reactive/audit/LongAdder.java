package com.octo.reactive.audit;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pprados on 08/09/2014.
 */
//public class LongAdder
//{
//	private final LongAdderWrapper wrapper;
//
//	public LongAdder()
//	{
//		if (Integer.parseInt(System.getProperty("java.version").split("\\.")[1]) >= 8)
//			wrapper = new Java8LongAdder();
//		else
//			wrapper = new Java7LongAdder();
//	}
//
//	public long sum()
//	{
//		return wrapper.sum();
//	}
//
//	public void add(long l)
//	{
//		wrapper.add(l);
//	}
//
//	public void reset()
//	{
//		wrapper.reset();
//	}
//
//	public interface LongAdderWrapper
//	{
//		long sum();
//
//		void add(long l);
//
//		void reset();
//	}
//
//	static class Java7LongAdder implements LongAdderWrapper
//	{
//		private AtomicLong adder = new AtomicLong(0);
//
//		public long sum()
//		{
//			return adder.get();
//		}
//
//		public void add(long l)
//		{
//			adder.addAndGet(l);
//		}
//
//		public void reset()
//		{
//			adder.set(0);
//		}
//	}
//
//	// Compile with Java8 but use with Java7+
//	static class Java8LongAdder implements LongAdderWrapper
//	{
//		private java.util.concurrent.atomic.LongAdder adder=new java.util.concurrent.atomic.LongAdder();
//
//		public long sum()
//		{
//			return adder.sum();
//		}
//
//		public void add(long l)
//		{
//			adder.add(l);
//		}
//
//		public void reset()
//		{
//			adder.reset();
//		}
//	}
//}
