package com.octo.reactive.audit.java.net;

import com.octo.reactive.audit.ConfigAuditReactive;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import com.octo.reactive.audit.lib.NetworkAuditReactiveException;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static com.octo.reactive.audit.IOTestTools.HOST;
import static com.octo.reactive.audit.IOTestTools.PORT;

/**
 * Created by pprados on 06/05/14.
 */
public class URLTest
{
	@Test(expected = NetworkAuditReactiveException.class)
	public void openStream_network() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		URL url = new URL("http://" + HOST + ":" + PORT);
		url.openStream();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void openStream_file() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		URL url = new URL("file://" + HOST + ":" + PORT);
		url.openStream();
	}

	@Test(expected = FileAuditReactiveException.class)
	public void openStream_jarfile() throws IOException
	{
		ConfigAuditReactive.strict.commit();
		URL url = new URL("JAR:file:///toto!/toto" + HOST + ":" + PORT);
		url.openStream();
	}
}
