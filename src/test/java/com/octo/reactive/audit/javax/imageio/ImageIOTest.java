package com.octo.reactive.audit.javax.imageio;

import com.octo.reactive.audit.AuditReactive;
import com.octo.reactive.audit.IOTestTools;
import com.octo.reactive.audit.lib.FileAuditReactiveException;
import org.junit.Test;

import javax.activation.FileDataSource;
import java.io.IOException;

public class ImageIOTest
{
	@Test(expected = FileAuditReactiveException.class)
	public void new_File() throws InterruptedException, IOException
	{
		AuditReactive.strict.commit();
		new FileDataSource(IOTestTools.getTempFile());
	}
	@Test(expected = FileAuditReactiveException.class)
	public void new_String() throws InterruptedException, IOException
	{
		AuditReactive.strict.commit();
		new FileDataSource(IOTestTools.getTempFile().getAbsolutePath());
	}
}
