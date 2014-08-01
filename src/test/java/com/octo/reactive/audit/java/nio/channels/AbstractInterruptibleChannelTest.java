package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.lib.AuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.spi.AbstractInterruptibleChannel;

abstract class AbstractInterruptibleChannelTest
{
	@Test(expected = AuditReactiveException.class)
	public void begin()
	{
		AuditReactive.strict.commit();
		class C extends AbstractInterruptibleChannel
		{

			@Override
			protected void implCloseChannel() throws IOException
			{

			}

			public void doBegin()
			{
				begin();
			}
		}
		new C().doBegin();
	}

}
