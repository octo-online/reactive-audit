package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ForkJoinPool.commonPool;

/**
 * Created by pprados on 18/06/2014.
 */
public class AsynchronousChannelGroupTest
{

	@Test(expected = AuditReactiveException.class)
	public void awaitTermination() throws IOException, InterruptedException
	{
		AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(commonPool());
		group.awaitTermination(1, TimeUnit.NANOSECONDS);
	}

}
