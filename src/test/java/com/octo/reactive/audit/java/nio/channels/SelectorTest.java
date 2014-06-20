package com.octo.reactive.audit.java.nio.channels;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * Created by pprados on 18/06/2014.
 */
public class SelectorTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void select() throws IOException
	{
		try (Selector r = Selector.open())
		{
			ConfigAuditReactive.strict.commit();
			r.select();
		}
	}

	@Test(expected = NetworkAuditReactiveException.class)
	public void select_l() throws IOException
	{
		try (Selector r = Selector.open())
		{
			ConfigAuditReactive.strict.commit();
			r.select(100);
		}
	}
}
